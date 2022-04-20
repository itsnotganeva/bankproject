package by.ganevich.repository;

import by.ganevich.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Rate findRateByCurrency(int currency);
}
