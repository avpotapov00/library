package info.potapov.library.reader.factory;

import info.potapov.library.reader.entity.Reader;
import info.potapov.library.reader.entity.ReaderInfo;
import org.springframework.stereotype.Component;

@Component
public class ReaderFactory {

    public Reader create(ReaderInfo readerInfo) {
        return Reader.builder()
                .userName(readerInfo.getReaderName())
                .build();
    }

}
