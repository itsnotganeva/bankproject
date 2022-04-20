package by.ganevich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionDto {

    private Integer clientType;

    @Pattern(regexp = "\\(?\\d+\\.\\d+\\)?")
    @NotEmpty(message = "Commission must not be empty")
    private String commission;
}
