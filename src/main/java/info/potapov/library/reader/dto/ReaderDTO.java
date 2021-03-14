package info.potapov.library.reader.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReaderDTO {

    @NotBlank
    private String readerName;

}
