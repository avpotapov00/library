package info.potapov.library.book.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private Long id;

    private String code;

    private String name;

    private String author;

}
