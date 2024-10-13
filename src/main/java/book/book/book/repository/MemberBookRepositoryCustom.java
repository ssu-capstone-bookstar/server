package book.book.book.repository;

import book.book.book.dto.MyCommentResponse;
import book.book.book.entity.MemberBook;
import book.book.book.entity.ReadingStatus;
import book.book.book.sort.SortType;

import java.util.List;

public interface MemberBookRepositoryCustom {
    List<MemberBook> findBookByReadingStatus(Long memberId, ReadingStatus readingStatus, SortType sortType, Long cursorId, Integer pageSize);

    List<MyCommentResponse> findCommentsByMemberId(Long memberId, SortType sortType, Long cursorId, Integer pageSize);
}
