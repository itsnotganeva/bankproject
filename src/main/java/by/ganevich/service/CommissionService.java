package by.ganevich.service;

import by.ganevich.entity.Bank;
import by.ganevich.entity.ClientType;
import by.ganevich.entity.Commission;
import by.ganevich.repository.CommissionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommissionService {

    private final CommissionRepository commissionRepository;

    public Double findCommissionByClientTypeAndBank(Integer clientType, Bank bank) {
        Commission commission = commissionRepository.findCommissionByClientTypeAndBank(clientType, bank);
        return commission.getCommission();
    }

    public Commission findByBankAndClientType(Bank bank, ClientType clientType) {
        Commission commission = commissionRepository.findByBankAndClientType(bank, clientType.ordinal());
        return commission;
    }

    public void saveCommission(Commission commission) {
        commissionRepository.save(commission);
    }

    public void delete(Commission commission) {
        commissionRepository.delete(commission);
    }
}
