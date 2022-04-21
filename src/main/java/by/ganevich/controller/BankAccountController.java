package by.ganevich.controller;

import by.ganevich.dto.BankAccountDto;
import by.ganevich.dto.BankDto;
import by.ganevich.dto.ClientDto;
import by.ganevich.dto.CreateBankAccountDto;
import by.ganevich.entity.Bank;
import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import by.ganevich.mapper.interfaces.BankAccountMapper;
import by.ganevich.mapper.interfaces.BankMapper;
import by.ganevich.mapper.interfaces.ClientMapper;
import by.ganevich.service.BankAccountService;
import by.ganevich.service.BankService;
import by.ganevich.service.ClientService;
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

import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
@Tag(name = "Bank account controller", description = "To manage bank accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final CustomValidator<BankAccountDto> bankAccountValidator;
    private final BankAccountMapper bankAccountMapper;

    private final BankService bankService;
    private final BankMapper bankMapper;
    private final ClientService clientService;
    private final ClientMapper clientMapper;


    @GetMapping(value = "/bank-accounts")
    public String addAccount(Model model) {
        model.addAttribute("account", new CreateBankAccountDto());
        return "createBankAccount";
    }

    @PostMapping(value = "/bank-accounts")
    @Operation(
            summary = "Bank account creation",
            description = "Allows to create a new bank account"
    )
    public String create(
            @ModelAttribute @Parameter(description = "bank account to be added to the database")
                    CreateBankAccountDto createBankAccountDto
    ) {
        log.info("REST: Create bank account is called");
        Client client = clientService.findClientByName(createBankAccountDto.getClientName());
        ClientDto clientDto = clientMapper.toDto(client);
        Bank bank = bankService.findBankByName(createBankAccountDto.getBankName());
        BankDto bankDto = bankMapper.toDto(bank);

        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setBankProducer(bankDto);
        bankAccountDto.setAmountOfMoney(createBankAccountDto.getAmountOfMoney());
        bankAccountDto.setCurrency(createBankAccountDto.getCurrency());
        bankAccountDto.setOwner(clientDto);
        bankAccountDto.setNumber(createBankAccountDto.getNumber());

        if (!bankAccountValidator.validateDto(bankAccountDto)) {
            log.error("REST: input of bank data is invalid");
            return "result";
        }
        BankAccount bankAccount = bankAccountMapper.toEntity(bankAccountDto);
        bankAccountService.save(bankAccount);
        log.info("REST: bank is created successfully");
        return "result";
    }

    @GetMapping(value = "/bank-accounts/get/{id}")
    @Operation(
            summary = "Reading bank accounts",
            description = "Allows to read all bank accounts of client"
    )
    public String read(@PathVariable(name = "id") @Parameter(description = "id of bank account") Long id, Model model) {
        log.info("REST: Read bank account of client " +id + " is called");
        Optional<BankAccount> bankAccount = bankAccountService.findBankAccountById(id);
        BankAccountDto bankAccountDto = bankAccountMapper.toDto(bankAccount.get());
        model.addAttribute("account", bankAccountDto);

        log.info("REST: Reading of account was successful");
        return "bankAccount";
    }


}