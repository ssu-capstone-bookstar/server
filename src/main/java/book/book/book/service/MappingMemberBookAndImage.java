package book.book.book.service;

import book.book.book.dto.MemberBookResponse;
import book.book.book.entity.MemberBook;
import book.book.collection.dto.CollectionBookResponse;
import book.book.collection.entity.CollectionBook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MappingMemberBookAndImage {

    /**
     * 생성자 호출 안되게: 유틸클래스로 생성자 private으로 보호
     */
    private MappingMemberBookAndImage() {
    }

    /**
     * CollectionResponses로직이랑 똑같음 / 통합 생각
     */
    public static List<MemberBookResponse> mapping(List<MemberBook> memberBooks, Map<Long, String> imagesMap) {
        List<MemberBookResponse> memberBookResponses = new ArrayList<>();

        for (MemberBook memberBook : memberBooks) {
            String imagePath = imagesMap.get(memberBook.getBook().getId());

            MemberBookResponse response = MemberBookResponse.of(memberBook, imagePath);

            memberBookResponses.add(response);
        }

        return memberBookResponses;
    }

    public static List<CollectionBookResponse> mappingCollectinoBook(List<CollectionBook> collectionBooks, Map<Long, String> imagesMap) {
        List<CollectionBookResponse> collectionBookResponses = new ArrayList<>();

        for (CollectionBook collectionBook : collectionBooks) {
            String imagePath = imagesMap.get(collectionBook.getBook().getId());

            CollectionBookResponse response = CollectionBookResponse.of(collectionBook, imagePath);

            collectionBookResponses.add(response);
        }

        return collectionBookResponses;
    }
}
