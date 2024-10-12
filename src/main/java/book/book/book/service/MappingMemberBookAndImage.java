package book.book.book.service;

import book.book.book.dto.BookThumbnailResponse;
import book.book.book.entity.MemberBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MappingMemberBookAndImage {

    /**
     * CollectionResponses로직이랑 똑같음 / 통합 생각
     */
    public static List<BookThumbnailResponse> mapping(List<MemberBook> memberBooks, Map<Long, String> imagesMap) {
        List<BookThumbnailResponse> bookThumbnailResponses = new ArrayList<>();

        for (MemberBook memberBook : memberBooks) {
            String imagePath = imagesMap.get(memberBook.getBook().getId());

            BookThumbnailResponse response = BookThumbnailResponse.of(memberBook, imagePath);

            bookThumbnailResponses.add(response);
        }

        return bookThumbnailResponses;
    }

    /**
     * 생성자 호출 안되게: 유틸클래스로 생성자 private으로 보호
     */
    private MappingMemberBookAndImage() {
    }
}
