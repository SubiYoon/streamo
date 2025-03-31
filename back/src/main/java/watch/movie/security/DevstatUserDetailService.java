package watch.movie.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import watch.movie.domain.member.service.MemberService;
import watch.movie.entity.Member;
import watch.movie.utility.ItemCheck;

@Service
@RequiredArgsConstructor
public class DevstatUserDetailService implements UserDetailsService {

    @Autowired
    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberService.findById(id);
        if(!ItemCheck.isEmpty(member)){
            throw new UsernameNotFoundException("user not found");
        }

        return member;
    }
}
