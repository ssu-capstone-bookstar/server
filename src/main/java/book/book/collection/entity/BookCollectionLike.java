package book.book.collection.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collection_like")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCollectionLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private BookCollection bookCollection;

    public BookCollectionLike(Member member, BookCollection bookCollection) {
        this.member = member;
        this.bookCollection = bookCollection;
    }
}
