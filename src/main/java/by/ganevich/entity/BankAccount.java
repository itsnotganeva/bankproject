package by.ganevich.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "bankAccounts")
@NamedEntityGraph(
        name = "bankAccounts-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("owner"),
                @NamedAttributeNode(value = "bankProducer", subgraph = "banks-sub-graph"),
                @NamedAttributeNode("sentTransactions"),
                @NamedAttributeNode("receivedTransactions")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "banks-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("commissions")
                        }
                )
        }

)
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "amountOfMoney")
    private Double amountOfMoney;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "clientId")
    private Client owner;

    @ManyToOne(
            cascade = CascadeType.DETACH,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "bankId")
    private Bank bankProducer;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "senderAccount"
    )
    private Set<Transaction> sentTransactions;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "receiverAccount"
    )
    private Set<Transaction> receivedTransactions;

    @Override
    public String toString() {
        return "BankAccount{"
                + "currency=" + currency
                + ", amountOfMoney=" + amountOfMoney
                + ", bankProducer=" + bankProducer
                + '}';
    }
}
