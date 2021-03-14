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

    /**
     * Saves the reader
     *
     * @param reader reader
     */
    @Override
    public void save(Reader reader) {
        jdbcTemplate.update("insert into reader (name) values(?)", reader.getUserName());
    }

    /**
     * Total number of readers
     *
     * @return number or readers
     */
    @Override
    public int count() {
        String sql = "select count(*) from reader";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    /**
     * Checks that a reader with this number exists
     *
     * @param cardNumber reader catd number
     * @return result of check
     */
    @Override
    public boolean existsByCardNumber(long cardNumber) {
        String sql = "select count(*) from reader where card_number = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, cardNumber);
        return count != null && count != 0;
    }

    /**
     * Finds readers in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of readers
     */
    @Override
    public List<Reader> findReadersInRange(int from, int limit) {
        String sql = "select r.* from reader r  order by r.card_number limit ? offset ?";
        return jdbcTemplate.query(sql, rowMapper, limit, from);
    }
}
