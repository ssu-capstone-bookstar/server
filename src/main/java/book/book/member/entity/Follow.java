package book.book.member.entity;

import book.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "follow")
@Getter
public class Follow extends BaseEntity {
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