package watch.movie.domain.member.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import watch.movie.domain.member.dto.MemberDto;
import watch.movie.entity.Member;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberService memberService;

    @Test
    public void join() throws Exception {
        // given
        MemberDto memberDto = new MemberDto();
        memberDto.setId("아이디");
        memberDto.setName("이름");
        memberDto.setPassword("비밀번호");
        memberDto.setBirthday("생년월일");

        // when
        memberService.join(memberDto);

        em.flush();
        em.clear();

        Member findMember = memberService.findById(memberDto.getId());
        // then

        Assertions.assertThat(findMember.getName()).isEqualTo("이름");


    }
}