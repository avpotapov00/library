package info.potapov.library.book.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {

    @NotBlank
    private String author;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

}
