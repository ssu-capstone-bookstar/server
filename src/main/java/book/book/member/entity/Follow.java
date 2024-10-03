package book.book.member.entity;

import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "follow")
@Getter
public class Follow extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private Member follower;

    @ManyToOne
    @JoinColumn(name = "following")
    private Member following;
}