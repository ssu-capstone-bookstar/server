package book.book.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MinimmumMemberProfile {

    private String nickName;
    private String profileImage;

    @QueryProjection
    public MinimmumMemberProfile(String nickName, String profileImage) {
        this.nickName = nickName;
        this.profileImage = profileImage;
    }
}
