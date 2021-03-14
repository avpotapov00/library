package info.potapov.library.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangeCodeDTO {

    @NotBlank
    private String oldCode;

    @NotBlank
    private String newCode;

}
