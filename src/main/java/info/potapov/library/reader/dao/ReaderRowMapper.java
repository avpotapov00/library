package info.potapov.library.reader.dao;

import info.potapov.library.reader.entity.Reader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReaderRowMapper implements RowMapper<Reader> {
    @Override
    public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Reader.builder()
                .cardNumber(rs.getLong("card_number"))
                .userName(rs.getString("name"))
                .build();
    }
}
