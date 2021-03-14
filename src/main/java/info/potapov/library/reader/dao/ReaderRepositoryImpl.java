package info.potapov.library.reader.dao;

import info.potapov.library.reader.entity.Reader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderRepositoryImpl implements ReaderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ReaderRowMapper rowMapper;

    public ReaderRepositoryImpl(JdbcTemplate jdbcTemplate, ReaderRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Reader reader) {
        jdbcTemplate.update("insert into reader (name) values(?)", reader.getUserName());
    }

    @Override
    public int count() {
        String sql = "select count(*) from reader";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public boolean existsById(long userId) {
        String sql = "select count(*) from reader where card_number = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null && count != 0;
    }

    @Override
    public List<Reader> findBookInRange(int from, int limit) {
        String sql = "select r.* from reader r limit ? offset ?";
        return jdbcTemplate.query(sql, rowMapper, limit, from);
    }
}
