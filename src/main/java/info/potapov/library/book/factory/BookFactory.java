package info.potapov.library.book.factory;

import info.potapov.library.book.entity.Book;
import info.potapov.library.book.entity.BookInfo;
import org.springframework.stereotype.Component;

@Component
public class BookFactory {

    /**
     * Creates a book
     *
     * @param info book info
     * @return created book
     */
    public Book create(BookInfo info) {
        return Book.builder()
                .id(info.getId())
                .code(info.getCode())
                .author(info.getAuthor())
                .name(info.getName())
                .build();
    }


}
