package by.ganevich.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions")
@NamedEntityGraph(
        name = "transactions-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("sender"),
                @NamedAttributeNode("receiver")
        }
)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "senderId")
    private Client sender;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "receiverId")
    private Client receiver;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "senderAccountId")
    private BankAccount senderAccount;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "receiverAccountId")
    private BankAccount receiverAccount;

    @Column(name = "amountOfMoney")
    private Double amountOfMoney;

    @Column(name = "date")
    private Date date;

    @Override
    public String toString() {
        return "Transaction{"
                + "id=" + id
                + ", sender=" + sender
                + ", receiver=" + receiver
                + ", amountOfMoney=" + amountOfMoney
                + ", date=" + date
                + '}';
    }
}
