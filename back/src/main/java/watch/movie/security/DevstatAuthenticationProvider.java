package watch.movie.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import watch.movie.entity.Member;

@Component
@RequiredArgsConstructor
public class DevstatAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String loginPwd = (String)authentication.getCredentials();

        Member member = (Member) userDetailsService.loadUserByUsername(loginId);

        if(!passwordEncoder.matches(loginPwd, member.getPassword())){
            throw new BadCredentialsException("password is not matched");
        }

        member.changePassword(null);

        return new DevstatAuthenticationToken(member, loginPwd, member.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(DevstatAuthenticationToken.class);
    }
}
