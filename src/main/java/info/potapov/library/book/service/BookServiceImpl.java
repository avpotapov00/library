package info.potapov.library.book.service;

import info.potapov.library.book.entity.Book;
import info.potapov.library.book.factory.BookFactory;
import info.potapov.library.book.entity.BookInfo;
import info.potapov.library.book.dao.BookRepository;
import info.potapov.library.exceptions.DataConflictException;
import info.potapov.library.exceptions.ReaderNotFoundException;
import info.potapov.library.reader.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final BookFactory factory;

    private final ReaderService readerService;

    public BookServiceImpl(BookRepository repository, BookFactory bookFactory, ReaderService readerService) {
        this.repository = repository;
        this.factory = bookFactory;
        this.readerService = readerService;
    }

    /**
     * Returns books belonging to the reader
     *
     * @param readerCardNumber reader card number
     * @return list of books
     */
    @Override
    public List<Book> getBooksByReader(long readerCardNumber) {
        if (!readerService.isUserExists(readerCardNumber)) {
            throw new ReaderNotFoundException("User not found with id: " + readerCardNumber);
        }
        return repository.findByUser(readerCardNumber);
    }

    /**
     * Finds books with the given code
     *
     * @param code book code
     * @return book
     */
    @Override
    public Book getBook(String code) {
        return repository.findBook(code);
    }

    /**
     * Creates and saves a book
     *
     * @param bookInfo book info
     */
    @Override
    public void create(BookInfo bookInfo) {
        Book book = factory.create(bookInfo);
        checkCodeNotExists(book.getCode());
        repository.save(book);
    }

    /**
     * Deletes the book by code
     *
     * @param code book code
     */
    @Override
    public void deleteBook(String code) {
        repository.deleteByCode(code);
    }

    @Override
    public void changeCode(String oldCode, String newCode) {
        checkCodeNotExists(newCode);
        repository.updateCode(oldCode, newCode);
    }

    @Override
    public int getBooksCount() {
        return repository.count();
    }

    @Override
    public int getBooksWithOwnerCount() {
        return repository.findBooksWithOwnerCount();
    }

    @Override
    public List<Book> getBooksOfRange(int from, int limit) {
        return repository.findBookInRange(from, limit);
    }

    private void checkCodeNotExists(String code) {
        if (repository.existsWithCode(code)) {
            throw new DataConflictException("Book with code " + code + " already exists");
        }
    }
}
