package by.ganevich.repository;

import by.ganevich.entity.Bank;
import by.ganevich.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

    Commission findCommissionByClientTypeAndBank(Integer clientType, Bank bank);

    Commission findByBankAndClientType(Bank bank, Integer clientType);
}
