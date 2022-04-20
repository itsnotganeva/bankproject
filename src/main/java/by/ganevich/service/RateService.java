package by.ganevich.service;

import by.ganevich.entity.Rate;
import by.ganevich.repository.RateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class RateService {

    private final RateRepository rateRepository;

    public Double findRateByCurrency(int currency) {
        Rate rate = rateRepository.findRateByCurrency(currency);
        return rate.getRate();
    }
}
