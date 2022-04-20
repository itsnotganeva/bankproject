package by.ganevich.service;

import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import by.ganevich.entity.Transaction;
import by.ganevich.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TransactionService implements BaseService<Transaction> {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final RateService rateService;
    private final CommissionService commissionService;

    public void sendMoney(Integer senderAccountNumber, Integer receiverAccountNumber, Double sumOfMoney) {

        log.info("TransactionService: Send money is called.");

        BankAccount senderAccount = bankAccountService.findBankAccountByNumber(senderAccountNumber);
        BankAccount recipientAccount = bankAccountService.findBankAccountByNumber(receiverAccountNumber);

        Double senderSum = senderAccount.getAmountOfMoney();
        Double recipientSum = recipientAccount.getAmountOfMoney();

        if (sumOfMoney <= senderSum) {
            Double convertSum = sumOfMoney
                            * rateService.findRateByCurrency(senderAccount.getCurrency().ordinal())
                            / rateService.findRateByCurrency(recipientAccount.getCurrency().ordinal());

            Double sumWithCommission;

            if (senderAccount.getBankProducer().getName().equals(recipientAccount.getBankProducer().getName())) {
                sumWithCommission = sumOfMoney;
            } else {
                sumWithCommission = sumOfMoney
                        + sumOfMoney
                        * commissionService
                        .findCommissionByClientTypeAndBank(senderAccount.getOwner().getType().ordinal(),
                                senderAccount.getBankProducer());
            }

            senderAccount.setAmountOfMoney(senderSum - sumWithCommission);
            recipientAccount.setAmountOfMoney(recipientSum + convertSum);

            bankAccountService.save(senderAccount);
            bankAccountService.save(recipientAccount);

            Transaction transaction = new Transaction();
            long millis = System.currentTimeMillis();
            transaction.setDate(new Date(millis));
            transaction.setAmountOfMoney(sumOfMoney);
            transaction.setSenderAccount(senderAccount);
            transaction.setReceiverAccount(recipientAccount);
            transaction.setSender(senderAccount.getOwner());
            transaction.setReceiver(recipientAccount.getOwner());
            transactionRepository.save(transaction);

            log.info("Transaction from " + senderAccount.getOwner() + " to " + recipientAccount.getOwner() + "was carried successfully.");
        }
    }

    public Set<Transaction> readAllByDateAndSender(Date dateBefore, Date dateAfter, Client client) {

        log.info("TransactionService: Read all by date and sender is called.");

        Set<Transaction> transactions =
                transactionRepository.findAllByDateBetweenAndSender(dateBefore, dateAfter, client);

        log.info("Transactions of sender " + client.getName() + " by date between " + dateBefore + " and " + dateAfter + "are successfully found.");

        return transactions;
    }

    public Set<Transaction> readAllByDateAndReceiver(Date dateBefore, Date dateAfter, Client client) {

        log.info("TransactionService: Read all by date and receiver is called.");

        Set<Transaction> transactions =
                transactionRepository.findAllByDateBetweenAndReceiver(dateBefore, dateAfter, client);
        log.info("Transactions of receiver " + client.getName() + " by date between " + dateBefore + " and " + dateAfter + "are successfully found.");

        return transactions;
    }

    public List<Transaction> readAllByClientId (Date dateBefore, Date dateAfter, Long id) {
        List<Transaction> transactions =
                transactionRepository.findAllByDateBetweenAndSenderIdOrReceiverId(dateBefore, dateAfter, id, id);

        log.info("Transactions of client " + id + " by date between " + dateBefore + " and " + dateAfter + "are successfully found.");
        return transactions;
    }

    public List<Transaction> readAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        log.info("The list of all transactions in successfully read.");
        return transactions;
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
        log.info("Transactions is saved");
    }
}
