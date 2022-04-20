package by.ganevich.repository;

import by.ganevich.entity.Bank;
import by.ganevich.entity.BankAccount;
import by.ganevich.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @EntityGraph(value = "bankAccounts-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    BankAccount findBankAccountByOwnerAndBankProducer(Client owner, Bank bankProducer);

    @EntityGraph(value = "bankAccounts-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Set<BankAccount> findBankAccountByOwner(Client owner);

    List<BankAccount> findBankAccountByOwnerId(Long id);

    BankAccount findBankAccountByNumber(Integer number);
}
