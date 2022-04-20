package by.ganevich.repository;

import by.ganevich.entity.Bank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @EntityGraph(value = "banks-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Bank findByName(String name);

    @EntityGraph(value = "banks-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    List<Bank> findAll();

    Optional<Bank> findById(Long id);

}
