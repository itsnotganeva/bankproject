package by.ganevich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionToViewDto {

    @Pattern(regexp = "^sent$|^received$")
    @NotEmpty(message = "Type must not be empty")
    private String type;

    @Valid
    @NotNull(message = "Client with entered name not found!")
    private ClientDto client;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @NotEmpty(message = "DateBefore must not be empty")
    private String dateBefore;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @NotEmpty(message = "DateAfter must not be empty")
    private String dateAfter;

}
