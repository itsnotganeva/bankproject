package by.ganevich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDto {

    private Long id;

    @Size(min = 5, max = 5)
    @NotEmpty(message = "Account number must not be empty")
    private String number;

    @Pattern(regexp = "^USD$|^EUR$|^BYN$")
    @NotEmpty(message = "Currency must not be empty")
    private String currency;

    @Pattern(regexp = "\\(?\\d+\\.\\d+\\)?")
    @NotEmpty(message = "Amount of money must not be empty")
    private String amountOfMoney;

    @Valid
    @NotNull(message = "Client with entered name not found!")
    private ClientDto owner;

    @Valid
    @NotNull(message = "Bank with entered name not found!")
    private BankDto bankProducer;
}
