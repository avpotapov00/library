package info.potapov.library.reader.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reader {

    private Long cardNumber;

    private String userName;

}
