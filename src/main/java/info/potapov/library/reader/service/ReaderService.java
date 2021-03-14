package info.potapov.library.reader.service;

import info.potapov.library.reader.entity.Reader;
import info.potapov.library.reader.entity.ReaderInfo;

import java.util.List;

public interface ReaderService {

    /**
     * Creates and saves a reader
     *
     * @param readerInfo reader info
     */
    void createReader(ReaderInfo readerInfo);

    /**
     * Total number of readers
     *
     * @return number of readers
     */
    int getReadersCount();

    /**
     * Checks that a reader with this number exists
     *
     * @param readerCardNumber reader catd number
     * @return result of check
     */
    boolean isReaderExists(long readerCardNumber);

    /**
     * Finds readers in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of readers
     */
    List<Reader> getReadersOfRange(int from, int limit);
}
