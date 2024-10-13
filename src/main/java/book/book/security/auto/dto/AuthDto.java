package book.book.security.auto.dto;

import book.book.member.entity.MemberRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthDto {

    private final Long id;
    private final String providerId;
    private final String email;
    private final MemberRole role;

}
