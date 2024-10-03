package book.book.book.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReadingStatus {
    READING("I'm reading"),
    WANT_TO_READ("I want to read"),
    READED("I've read everything");

    private final String description;

}