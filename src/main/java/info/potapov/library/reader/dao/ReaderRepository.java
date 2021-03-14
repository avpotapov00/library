package info.potapov.library.reader.dao;

import info.potapov.library.reader.entity.Reader;

import java.util.List;

public interface ReaderRepository {

    void save(Reader reader);

    int count();

    boolean existsById(long userId);

    List<Reader> findBookInRange(int from, int limit);
}
