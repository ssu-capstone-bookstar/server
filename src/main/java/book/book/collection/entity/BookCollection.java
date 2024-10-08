package book.book.collection.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collection")
@Getter
@NoArgsConstructor
public class BookCollection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String name;
    private String description;

    public BookCollection(Member member, String name, String description) {
        this.member = member;
        this.name = name;
        this.description = description;
    }
}