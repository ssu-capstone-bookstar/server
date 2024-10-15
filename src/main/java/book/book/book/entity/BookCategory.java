package book.book.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BookCategory {
    LITERATURE(1, "문학"),
    NOVEL(2, "소설"),
    POETRY(3, "시"),
    ESSAY(4, "에세이"),
    PHILOSOPHY(5, "철학"),
    HISTORY(6, "역사"),
    SCIENCE(7, "과학"),
    TECHNOLOGY(8, "기술"),
    ART(9, "예술"),
    MUSIC(10, "음악"),
    ECONOMICS(11, "경제"),
    POLITICS(12, "정치"),
    EDUCATION(13, "교육"),
    CHILDREN(14, "어린이"),
    COMICS(15, "만화"),
    TRAVEL(16, "여행"),
    COOKING(17, "요리"),
    HEALTH(18, "건강"),
    SELF_HELP(19, "자기계발"),
    RELIGION(20, "종교"),
    OTHER(21,"기타");

    private final int value;
    private final String koreanName;


    // TODO
    // 값으로부터 Enum 상수를 찾는 메서드
    public static BookCategory fromValue(int value) {
        for (BookCategory category : values()) {
            if (category.value == value) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid BookCategory value: " + value);
    }

    // TODO
    // 한국어 이름으로부터 Enum 상수를 찾는 메서드
    public static BookCategory fromKoreanName(String koreanName) {
        for (BookCategory category : values()) {
            if (category.koreanName.equals(koreanName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid BookCategory Korean name: " + koreanName);
    }

    public static BookCategory fromAladinCategory(String aladinCategory) {

        if (aladinCategory.contains("소설")) return NOVEL;
        if (aladinCategory.contains("시")) return POETRY;
        if (aladinCategory.contains("에세이")) return ESSAY;
        if (aladinCategory.contains("철학")) return PHILOSOPHY;
        if (aladinCategory.contains("역사")) return HISTORY;
        if (aladinCategory.contains("과학")) return SCIENCE;
        if (aladinCategory.contains("기술")) return TECHNOLOGY;
        if (aladinCategory.contains("예술")) return ART;
        if (aladinCategory.contains("음악")) return MUSIC;
        if (aladinCategory.contains("경제")) return ECONOMICS;
        if (aladinCategory.contains("정치")) return POLITICS;
        if (aladinCategory.contains("교육")) return EDUCATION;
        if (aladinCategory.contains("어린이")) return CHILDREN;
        if (aladinCategory.contains("만화")) return COMICS;
        if (aladinCategory.contains("여행")) return TRAVEL;
        if (aladinCategory.contains("요리")) return COOKING;
        if (aladinCategory.contains("건강")) return HEALTH;
        if (aladinCategory.contains("자기계발")) return SELF_HELP;
        if (aladinCategory.contains("종교")) return RELIGION;

        return OTHER;  // 기본값으로 기타 카테고리 반환
    }
}