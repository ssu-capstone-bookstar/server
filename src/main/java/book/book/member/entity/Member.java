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

}