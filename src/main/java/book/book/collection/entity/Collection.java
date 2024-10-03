package book.book.collection.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "collection")
@Getter
public class Collection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}