package book.book.image;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ImageCategory {

    MEMBER_PROFILE("사용자 프로필 이미지"),
    BOOK("책 표지 이미지"),
    BOARD("게시글 이미지")
    ;

    private final String description;

}