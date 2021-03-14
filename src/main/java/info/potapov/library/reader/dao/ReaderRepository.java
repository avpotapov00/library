package info.potapov.library.reader.dao;

import info.potapov.library.reader.entity.Reader;

import java.util.List;

public interface ReaderRepository {

    /**
     * Saves the reader
     *
     * @param reader reader
     */
    void save(Reader reader);

    /**
     * Total number of readers
     *
     * @return number or readers
     */
    int count();

    /**
     * Checks that a reader with this number exists
     *
     * @param cardNumber reader catd number
     * @return result of check
     */
    boolean existsByCardNumber(long cardNumber);

    /**
     * Finds readers in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of readers
     */
    List<Reader> findReadersInRange(int from, int limit);
}
