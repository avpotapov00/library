package info.potapov.library.book.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookView {

    private String name;

    private String author;

    private String code;

}
