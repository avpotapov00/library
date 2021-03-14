package info.potapov.library.reader.service;

import info.potapov.library.reader.dao.ReaderRepository;
import info.potapov.library.reader.entity.Reader;
import info.potapov.library.reader.entity.ReaderInfo;
import info.potapov.library.reader.factory.ReaderFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository repository;

    private final ReaderFactory factory;

    public ReaderServiceImpl(ReaderRepository repository, ReaderFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    /**
     * Creates and saves a reader
     *
     * @param readerInfo reader info
     */
    @Override
    public void createReader(ReaderInfo readerInfo) {
        Reader reader = factory.create(readerInfo);
        repository.save(reader);
    }

    /**
     * Total number of readers
     *
     * @return number of readers
     */
    @Override
    public int getReadersCount() {
        return repository.count();
    }

    /**
     * Checks that a reader with this number exists
     *
     * @param readerCardNumber reader catd number
     * @return result of check
     */
    @Override
    public boolean isReaderExists(long readerCardNumber) {
        return repository.existsByCardNumber(readerCardNumber);
    }

    /**
     * Finds readers in the specified interval in the sort order of their id
     *
     * @param from  start position
     * @param limit interval size
     * @return list of readers
     */
    @Override
    public List<Reader> getReadersOfRange(int from, int limit) {
        return repository.findReadersInRange(from, limit);
    }

}
