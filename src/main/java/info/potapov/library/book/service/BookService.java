package info.potapov.library.book.service;

import info.potapov.library.book.entity.Book;
import info.potapov.library.book.entity.BookInfo;
import info.potapov.library.exceptions.*;

import java.util.List;

public interface BookService {

    /**
     * Returns books belonging to the reader
     *
     * @param readerCardNumber reader card number
     * @return list of books
     */
    List<Book> getBooksByReader(long readerCardNumber);


    /**
     * Finds books with the given code
     *
     * @param code book code
     * @return book
     */
    Book getBook(String code);

    /**
     * Creates and saves a book
     *
     * @param bookInfo book info
     */
    void create(BookInfo bookInfo);

    /**
     * Deletes the book by code
     *
     * @param code book code
     */
    void deleteBook(String code);

    /**
     * Changes the code of the book
     *
     * @param oldCode old code
     * @param newCode new code
     */
    void changeCode(String oldCode, String newCode);

    /**
     * Total number of books
     *
     * @return books number
     */
    int getBooksCount();

    /**
     * Total number of books without owner
     *
     * @return books number
     */
    int getBooksWithOwnerCount();

    /**
     * Finds books in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of books
     */
    List<Book> getBooksInRange(int from, int limit);
}
