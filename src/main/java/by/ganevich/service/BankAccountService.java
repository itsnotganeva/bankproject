package by.ganevich.service;

import by.ganevich.entity.Bank;
import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import by.ganevich.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class BankAccountService implements BaseService<BankAccount> {

    private final BankAccountRepository bankAccountRepository;

    public void save(BankAccount bankAccount) {
        log.info("BankAccountService: Save of bank accounts is called");
        bankAccountRepository.save(bankAccount);
        log.info("Bank account " + bankAccount.getId() + " successfully created.");
    }

    public BankAccount getAccountByClientAndBank(Client client, Bank bank) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByOwnerAndBankProducer(client, bank);
        log.info("Bank account " + bankAccount.getId() + " successfully found.");
        return bankAccount;
    }

    public List<BankAccount> getByBank(Bank bank) {
        return bankAccountRepository.findBankAccountByBankProducer(bank);
    }

    public Set<BankAccount> getAllAccountsOfClient(Client client) {
        log.info("BankAccountService: Get all accounts of client is called.");
        Set<BankAccount> bankAccounts = bankAccountRepository.findBankAccountByOwner(client);
        log.info("Bank accounts of client " + client.getId() + " successfully found.");
        return bankAccounts;
    }

    public List<BankAccount> findBankAccountByClientId(Long id) {
        List<BankAccount> bankAccounts = bankAccountRepository.findBankAccountByOwnerId(id);
        log.info("Bank accounts of client " + id + " successfully found.");
        return bankAccounts;
    }

    public BankAccount findBankAccountByNumber(Integer number) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountByNumber(number);
        log.info("Bank account with number " + number + " successfully found.");
        return  bankAccount;
    }

    public List<BankAccount> readAll() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        log.info("List of bank accounts is successfully read.");
        return bankAccounts;
    }

    public Optional<BankAccount> findBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }
}
