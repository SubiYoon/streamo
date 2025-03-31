package watch.movie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import watch.movie.security.*;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * 정적 자원에 대해 보안을 적용하지 않도록 설정
     */
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    private final DevstatAuthenticationSuccessHandler devstatAuthenticationSuccessHandler;
    private final DevstatAuthenticationFailureHandler devstatAuthenticationFailureHandler;
    private final DevstatLoginAuthenticationEntryPoint profileAuthenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final DevstatAccessDeniedHandler devstatAccessDeniedHandler;

    /**
     * Spring Security 설정
     * <ul>
     *     <li>csrf 방지 기능 비활성화</li>
     *     <li>`/admin/**` 페이지만 인증 필요하게 설정</li>
     *     <li>로그인 성공시 `/`로 이동</li>
     *     <li>로그아웃시 세션삭제 및 쿠키삭제</li>
     * </ul>
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsFilter()))
                //인증 인가가 필요한 URL을 지정
                .authorizeHttpRequests(requests -> requests
                        //특정 패턴의 URL 인증이 필요함을 표시(authenticated())
//                        .requestMatchers(HttpMethod.POST).authenticated()
                        .requestMatchers(HttpMethod.DELETE).authenticated()
                        .requestMatchers(HttpMethod.PUT).authenticated()
                         //나머지 요청은 전부 허용
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthorizationFilter(), BasicAuthenticationFilter.class)
                .sessionManagement(sesssion -> sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(config -> config
                        .authenticationEntryPoint(profileAuthenticationEntryPoint)
                        .accessDeniedHandler(devstatAccessDeniedHandler))
                .logout(logout -> logout.logoutUrl("/logout")
                        .addLogoutHandler(new DevstatLogoutHandler())
                        .logoutSuccessHandler(new DevstatLogoutSuccessHandler()));

        return httpSecurity.build();
    }

    @Bean
    public DevstatAuthenticateionFilter ajaxAuthenticationFilter() throws Exception {
        DevstatAuthenticateionFilter devstatAuthenticateionFilter = new DevstatAuthenticateionFilter();
        devstatAuthenticateionFilter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        devstatAuthenticateionFilter.setAuthenticationSuccessHandler(devstatAuthenticationSuccessHandler);
        devstatAuthenticateionFilter.setAuthenticationFailureHandler(devstatAuthenticationFailureHandler);
        devstatAuthenticateionFilter.afterPropertiesSet();
        return devstatAuthenticateionFilter;
    }

    @Bean JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }

    @Bean
    public CorsConfigurationSource corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 쿠키 or 인증토큰을 포함하는 요청을 승인여부
        config.setAllowCredentials(true);
        // 요청 가능한 도메인 설정
        config.setAllowedOrigins(Collections.singletonList("*"));
        // 요청시 받고을 수 있는 헤더 설정
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "Content-Length", "X-Requested-Width", "X-XSRF-token"));
        // 요청시 가능한 Method 서정
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*", config);

        return source;
    }
}
