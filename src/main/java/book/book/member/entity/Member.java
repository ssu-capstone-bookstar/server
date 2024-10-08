package book.book.member.entity;

import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nickname;

    @Column
    private String profileImage;

    private Boolean privacy;

    @Column(length = 50)
    private String email;

    //카카오에서 주는 Id값
    @Column(length = 50)
    private String providerId;

    public Boolean isSameById(Long id) {
        return this.id == id ? true : false;
    }
}