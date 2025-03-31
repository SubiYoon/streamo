package watch.movie.domain.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import watch.movie.base.RoleCode;
import watch.movie.entity.Member;

@Data
@NoArgsConstructor
public class MemberDto {

    private String id;
    private String name;
    private String password;
    private String birthday;
    private RoleCode role;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.birthday = member.getBirthday();
        this.role = member.getRole();
    }
}
