package by.ganevich.io.commands;

import by.ganevich.dto.BankDto;
import by.ganevich.entity.Bank;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import by.ganevich.mapper.interfaces.BankMapper;
import by.ganevich.service.BankService;
import by.ganevich.service.CommissionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class DeleteBankCommand extends BaseCommand {

    private final String commandName = "deleteBank";
    private final CommissionService commissionService;
    private final BankService bankService;
    private final BankMapper bankMapper;

    @Valid
    private BankDto bankDto;

    @Override
    public String getDescriptionValue() {
        String description = "deleteBank bankName=?";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) {

        log.info("Delete bank command is called");
        String result;

        Bank bank = bankMapper.toEntity(bankDto);
        if (bank == null) {
            result = "Bank already removed!";
        } else {
            bankService.removeBank(bank);
            result = "Bank is successfully removed!";
        }

        CommandResult commandResult = new CommandResult();
        commandResult.setResult(result);

        log.info("Delete bank command is complete");
        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {

        BankDto bankDto = bankMapper
                .toDto(bankService.findBankByName(commandDescriptor.getParameters().get("bankName")));
        this.bankDto = bankDto;

        return this;
    }
}
