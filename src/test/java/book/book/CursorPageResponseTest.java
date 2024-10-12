package book.book;

import book.book.common.CursorPageResponse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CursorPageResponseTest {

    private final Long NO_NEXT_CURSOR = -1L;

    @Test
    void testIdExtraction_WithMoreDataThanPageSize() {
        // Given
        List<TestItem> items = Arrays.asList(
                new TestItem(1L, "Item 1"),
                new TestItem(2L, "Item 2"),
                new TestItem(3L, "Item 3"),
                new TestItem(4L, "Item 4")  // Extra item
        );
        int pageSize = 3;

        // When
        CursorPageResponse<TestItem> response = CursorPageResponse.of(
                items,
                pageSize,
                TestItem::getId
        );

        // Then
        assertEquals(3, response.getData().size());
        assertEquals(3L, response.getNextCursor());
        assertTrue(response.getHasNext());
    }

    @Test
    void testIdExtraction_WithExactPageSize() {
        // Given
        List<TestItem> items = Arrays.asList(
                new TestItem(1L, "Item 1"),
                new TestItem(2L, "Item 2"),
                new TestItem(3L, "Item 3")
        );
        int pageSize = 3;

        // When
        CursorPageResponse<TestItem> response = CursorPageResponse.of(
                items,
                pageSize,
                TestItem::getId
        );

        // Then
        assertEquals(3, response.getData().size());
        assertEquals(NO_NEXT_CURSOR, response.getNextCursor());
        assertFalse(response.getHasNext());
    }

    @Test
    void testIdExtraction_WithLessDataThanPageSize() {
        // Given
        List<TestItem> items = Arrays.asList(
                new TestItem(1L, "Item 1"),
                new TestItem(2L, "Item 2")
        );
        int pageSize = 3;

        // When
        CursorPageResponse<TestItem> response = CursorPageResponse.of(
                items,
                pageSize,
                TestItem::getId
        );

        // Then
        assertEquals(2, response.getData().size());
        assertEquals(NO_NEXT_CURSOR, response.getNextCursor());
        assertFalse(response.getHasNext());
    }

    @Test
    void testIdExtraction_WithEmptyList() {
        // Given
        List<TestItem> items = List.of();
        int pageSize = 3;

        // When
        CursorPageResponse<TestItem> response = CursorPageResponse.of(
                items,
                pageSize,
                TestItem::getId
        );

        // Then
        assertTrue(response.getData().isEmpty());
        assertEquals(NO_NEXT_CURSOR, response.getNextCursor());
        assertFalse(response.getHasNext());
    }

    // 테스트를 위한 내부 클래스
    private static class TestItem {
        private final Long id;
        private final String name;

        TestItem(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        Long getId() {
            return id;
        }
    }
}