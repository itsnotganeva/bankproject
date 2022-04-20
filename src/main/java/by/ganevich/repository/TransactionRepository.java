package by.ganevich.repository;

import by.ganevich.entity.Client;
import by.ganevich.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(value = "transactions-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Set<Transaction> findAllByDateBetweenAndSender(Date dateBefore, Date dateAfter, Client client);

    @EntityGraph(value = "transactions-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Set<Transaction> findAllByDateBetweenAndReceiver(Date dateBefore, Date dateAfter, Client client);

    List<Transaction> findAllByDateBetweenAndSenderIdOrReceiverId(Date dateBefore, Date dateAfter,
                                                                  Long senderId, Long receiverId);


}
