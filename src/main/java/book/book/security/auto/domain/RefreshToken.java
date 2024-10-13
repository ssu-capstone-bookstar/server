package book.book.security.auto.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
    @Id
    private Long memberId;

    @Column(name = "refresh_token")
    private String refreshToken;

//    @Column(name = "refresh_expiration")
//    private Date refreshExpiration;


    @Builder
    public RefreshToken(Long memberId, String refreshToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
        //this.refreshExpiration = refreshExpiration;
    }

    public RefreshToken updateRefreshToken(String newRefreshToken){
        this.refreshToken = newRefreshToken;
        return this;
    }
}
