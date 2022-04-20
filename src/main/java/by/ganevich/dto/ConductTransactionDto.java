package by.ganevich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConductTransactionDto {

    @Size(min = 5, max = 5)
    @NotEmpty(message = "Sender account number must not be empty")
    private String senderAccountNumber;

    @Size(min = 5, max = 5)
    @NotEmpty(message = "Receiver account number must not be empty")
    private String receiverAccountNumber;

    @Pattern(regexp = "\\(?\\d+\\.\\d+\\)?")
    @NotEmpty(message = "Amount of money must not be empty")
    private String amountOfMoney;

}
