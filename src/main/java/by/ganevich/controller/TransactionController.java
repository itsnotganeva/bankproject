package by.ganevich.controller;

import by.ganevich.dto.ConductTransactionDto;
import by.ganevich.dto.FindTransactionDto;
import by.ganevich.entity.Client;
import by.ganevich.entity.Transaction;
import by.ganevich.mapper.interfaces.TransactionMapper;
import by.ganevich.service.ClientService;
import by.ganevich.service.TransactionService;
import by.ganevich.validator.CustomValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.Set;

@Controller
@AllArgsConstructor
@Slf4j
@Tag(name = "Transaction controller", description = "To manage transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomValidator<ConductTransactionDto> transactionValidator;
    private final TransactionMapper transactionMapper;

    private final ClientService clientService;

    @GetMapping(value = "/transactions")
    public String getTransactionPage(Model model) {
        model.addAttribute("transaction", new ConductTransactionDto());
       return "transaction";
    }

    @GetMapping(value = "/transactions/get/{clientId}")
    public String findTransactions( @PathVariable(name = "clientId") @Parameter(description = "id of client") Long id, Model model) {
        model.addAttribute("trans", new FindTransactionDto());
        return "viewTransaction";
    }

    @GetMapping(value = "/transactions/info")
    @Operation(
            summary = "Reading transactions",
            description = "Allows to read all transactions of client by date"
    )
    public String read(
            @ModelAttribute FindTransactionDto findTransactionDto,
            Model model
    ) {

        Client client = clientService.findClientByName(findTransactionDto.getClientName());
        final Set<Transaction> transactions = transactionService
                .readAllByDateAndSender(Date.valueOf(findTransactionDto.getDateBefore()), Date.valueOf(findTransactionDto.getDateAfter()), client);

        model.addAttribute("trans", transactions);
        log.info("REST: Reading of transactions was successful");
        return "transactionInfo";
    }

    @PostMapping(value = "/transactions")
    @Operation(
            summary = "Сonducting transactions",
            description = "Allows to conduct transaction"
    )
    public String makeTransaction(
            @ModelAttribute @Parameter(description = "dto data to conduct transaction") ConductTransactionDto conductTransactionDto,
            Model model
    ) {
        log.info("REST: Make transaction is called");
        if (!transactionValidator.validateDto(conductTransactionDto)) {
            log.info("REST: The input data of transaction is invalid");
            return "result";
        }
        model.addAttribute("transaction", conductTransactionDto);
        transactionService.sendMoney(
                        Integer.valueOf(conductTransactionDto.getSenderAccountNumber()),
                        Integer.valueOf(conductTransactionDto.getReceiverAccountNumber()),
                        Double.valueOf(conductTransactionDto.getAmountOfMoney())
                );
        log.info("REST: Transaction was carried out successful");
        return "result";
    }
}
