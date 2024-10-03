package book.book.collection.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "collection_like")
@Getter
public class CollectionLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;
}
