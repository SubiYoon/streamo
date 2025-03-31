package watch.movie.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import watch.movie.domain.member.repository.MemberJpaRepository;
import watch.movie.entity.Member;
import watch.movie.utility.ItemCheck;

@Service
@RequiredArgsConstructor
public class DevstatUserDetailService implements UserDetailsService {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberJpaRepository.findById(id).orElse(null);
        if(!ItemCheck.isEmpty(member)){
            throw new UsernameNotFoundException("user not found");
        }

        return member;
    }
}
