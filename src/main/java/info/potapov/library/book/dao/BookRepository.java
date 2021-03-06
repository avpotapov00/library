package info.potapov.library.book.dao;

import info.potapov.library.book.entity.Book;

import java.util.List;
import java.util.Optional;
import info.potapov.library.exceptions.*;

public interface BookRepository {

    /**
     * Finds all books for the reader with the given id
     *
     * @param userId user id
     * @return list of books
     */
    List<Book> findByReader(long userId);

    /**
     * Finds books with the given code
     *
     * @param code book code
     * @return book
     */
    Book findBook(String code);

    /**
     * Saves given book
     *
     * @param book book to save
     */
    void save(Book book);


    /**
     * Deletes the book with given code
     *
     * @param code book code
     */
    void deleteByCode(String code);

    /**
     * Updates book code
     *
     * @param oldCode book old code
     * @param newCode book new code
     */
    void updateCode(String oldCode, String newCode);

    /**
     * Checks that book with given code exists
     *
     * @param code book code
     * @return check result
     */
    boolean existsWithCode(String code);

    /**
     * Number of books in library
     *
     * @return count
     */
    int count();

    /**
     * Number of books without owner
     *
     * @return number
     */
    int findBooksWithOwnerCount();


    /**
     * Finds books in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of books
     */
    List<Book> findBookInRange(int from, int limit);
}
