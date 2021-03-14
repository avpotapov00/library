package info.potapov.library.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReaderView {

    private Long cardNumber;

    private String name;

}
