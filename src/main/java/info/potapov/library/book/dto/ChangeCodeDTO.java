package info.potapov.library.book.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeCodeDTO {

    @NotBlank
    private String oldCode;

    @NotBlank
    private String newCode;

}
