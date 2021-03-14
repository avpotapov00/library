package info.potapov.library.view;

import info.potapov.library.reader.entity.Reader;
import org.springframework.stereotype.Component;

@Component
public class ReaderViewMapper {

    public ReaderView toView(Reader reader) {
        return ReaderView.builder()
                .cardNumber(reader.getCardNumber())
                .name(reader.getUserName())
                .build();
    }

}
