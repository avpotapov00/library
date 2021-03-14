package info.potapov.library.reader.service;

import info.potapov.library.reader.entity.Reader;
import info.potapov.library.reader.entity.ReaderInfo;

import java.util.List;

public interface ReaderService {

    void addUser(ReaderInfo readerInfo);

    int getReadersCount();

    boolean isUserExists(long userId);

    List<Reader> getReadersOfRange(int from, int limit);
}
