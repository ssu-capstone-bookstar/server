package book.book.recommending.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Recommending extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recommended_id", nullable = false)
    private Member recommended;

    @ManyToOne
    @JoinColumn(name = "recommender_id", nullable = false)
    private Member recommender;

    public Recommending(Member recommended, Member recommender) {
        this.recommended = recommended;
        this.recommender = recommender;
    }
}
