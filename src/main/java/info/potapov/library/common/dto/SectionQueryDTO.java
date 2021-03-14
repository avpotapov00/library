package info.potapov.library.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class SectionQueryDTO {

    @NotNull
    @PositiveOrZero
    private Integer from;

    @NotNull
    @Positive
    private Integer limit;

}
