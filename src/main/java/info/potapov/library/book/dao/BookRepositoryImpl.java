package info.potapov.library.book.dao;

import info.potapov.library.book.entity.Book;
import info.potapov.library.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    private final BookRowMapper bookRowMapper;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate, BookRowMapper bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }


    /**
     * Finds all books for the reader with the given id
     *
     * @param userId user id
     * @return list of books
     */
    @Override
    public List<Book> findByUser(long userId) {
        String sql = "select b.* from book b where b.owner_id = ?";
        return jdbcTemplate.query(sql, bookRowMapper, userId);
    }

    /**
     * Finds books with the given code
     *
     * @param code book code
     * @return book
     */
    @Override
    public Book findBook(String code) {
        String sql = "select * from book where code = ?";
        try {
            return jdbcTemplate.queryForObject(sql, bookRowMapper, code);
        } catch (Exception e) {
            throw new BookNotFoundException("book can not be found with id: " + code);
        }
    }

    /**
     * Saves given book
     *
     * @param book book to save
     */
    @Override
    public void save(Book book) {
        jdbcTemplate.update("insert into book (code, name, author) values (?, ?, ?)",
                book.getCode(), book.getName(), book.getAuthor());
    }

    /**
     * Deletes the book with given code
     *
     * @param code book code
     */
    @Override
    public void deleteByCode(String code) {
        int deletedAmount = jdbcTemplate.update("delete from book where code = ?", code);
        if (deletedAmount == 0) {
            throw new BookNotFoundException("book can not be found with code: " + code);
        }
    }

    /**
     * Updates book code
     *
     * @param oldCode book old code
     * @param newCode book new code
     */
    @Override
    public void updateCode(String oldCode, String newCode) {
        int updatedAmount = jdbcTemplate.update("update book set code = ? where code = ?", newCode, oldCode);
        if (updatedAmount == 0) {
            throw new BookNotFoundException("book can not be found with code: " + oldCode);
        }
    }

    /**
     * Checks that book with given code exists
     *
     * @param code book code
     * @return check result
     */
    @Override
    public boolean existsWithCode(String code) {
        String sql = "select count(*) from book where code = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, code);
        return count != null && count > 0;
    }

    /**
     * Number of books in library
     *
     * @return count
     */
    @Override
    public int count() {
        String sql = "select count(*) from book";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    /**
     * Number of books without owner
     *reader
     * @return number
     */
    @Override
    public int findBooksWithOwnerCount() {
        String sql = "select count(*) from book where owner is null";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    /**
     * Finds books in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of books
     */
    @Override
    public List<Book> findBookInRange(int from, int limit) {
        String sql = "select b.* from book b limit ? offset ?";
        return jdbcTemplate.query(sql, bookRowMapper, limit, from);
    }

}
