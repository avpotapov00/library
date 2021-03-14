package info.potapov.library.book.service;

import info.potapov.library.book.entity.Book;
import info.potapov.library.book.entity.BookInfo;

import java.util.List;

public interface BookService {

    List<Book> getBooksByUser(long userId);

    Book getBook(String code);

    void create(BookInfo bookInfo);

    void deleteBook(String code);

    void changeCode(String oldCode, String newCode);

    int getBooksCount();

    int getBooksWithOwnerCount();

    List<Book> getBooksOfRange(int from, int to);
}
