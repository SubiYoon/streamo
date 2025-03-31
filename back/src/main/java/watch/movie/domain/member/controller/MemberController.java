package watch.movie.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import watch.movie.base.RoleCode;
import watch.movie.base.StatusCode;
import watch.movie.domain.member.dto.MemberDto;
import watch.movie.domain.member.service.MemberService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> allMember() {
        log.info("MemberController.allMember -> {}", "전체 사용자 조회");

        return memberService.findAll();
    }

    @PostMapping("/member/join")
    public StatusCode join(@RequestBody MemberDto dto) {
        log.info("MemberController.join -> {}", "회원가입 시도");

        try {
            memberService.join(dto);
        } catch (DuplicateKeyException e) {
            return StatusCode.DUPLICATED_ID;
        }
        return StatusCode.SUCCESS;
    }

    @PostMapping("/member/role/{memberId}")
    public StatusCode updateRole(@PathVariable("memberId") String memberId, @RequestBody RoleCode role) {
        log.info("MemberController.updateRole -> {}", "회원 궎나 변경 시도");

        try {
            memberService.updateRole(memberId, role);
        } catch (UsernameNotFoundException e) {
            return StatusCode.USER_NOT_FOUND;
        }
        return StatusCode.SUCCESS;
    }

}
