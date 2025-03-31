package watch.movie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;
import java.util.UUID;

@Configuration
@SpringBootApplication
@RequiredArgsConstructor
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // null값 허용
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.ofNullable(
                        SecurityContextHolder.getContext().getAuthentication() != null
                                ? SecurityContextHolder.getContext().getAuthentication().getName()
                                : null
                );
            }
        };
    }
}
