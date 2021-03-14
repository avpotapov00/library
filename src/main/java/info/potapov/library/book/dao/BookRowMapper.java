package info.potapov.library.book.dao;

import info.potapov.library.book.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookRowMapper implements RowMapper<Book> {

    /**
     * Transforms given {@link ResultSet} to {@link Book}
     *
     * @param rs     resultSet
     * @param rowNum row number
     * @return book
     * @throws SQLException when row with given number not exists
     */
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .author(rs.getString("author"))
                .build();
    }
}
