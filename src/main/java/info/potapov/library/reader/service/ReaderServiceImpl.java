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

    @Override
    public void addUser(ReaderInfo readerInfo) {
        Reader reader = factory.create(readerInfo);
        repository.save(reader);
    }

    @Override
    public int getReadersCount() {
        return repository.count();
    }

    @Override
    public boolean isUserExists(long userId) {
        return repository.existsById(userId);
    }

    @Override
    public List<Reader> getReadersOfRange(int from, int limit) {
        return repository.findBookInRange(from, limit);
    }

}
