package book.book.book.repository;

import book.book.book.entity.BookCommentLike;
import book.book.book.entity.MemberBook;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCommentLikeRepository extends JpaRepository<BookCommentLike, Long> {


    Optional<BookCommentLike> findByMemberAndMemberBook(Member member, MemberBook memberBook);

    void deleteByMemberAndMemberBook(Member member, MemberBook memberBook);
}
