package info.potapov.library.book.view;

import info.potapov.library.book.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookViewMapper {


    public BookView toView(Book book) {
        return BookView.builder()
                .code(book.getCode())
                .author(book.getAuthor())
                .name(book.getName())
                .build();
    }
}
