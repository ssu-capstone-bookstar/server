package book.book.recommending;

import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Recommending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recommender_id", nullable = false)
    private Member recommender;

    @ManyToOne
    @JoinColumn(name = "recommended_id", nullable = false)
    private Member recommended;
}
