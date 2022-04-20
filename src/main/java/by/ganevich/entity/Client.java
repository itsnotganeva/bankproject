package by.ganevich.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "clients")
@NamedEntityGraph(
        name = "clients-entity-graph",
        attributeNodes = {
                @NamedAttributeNode(value = "bankAccounts", subgraph = "bankAccounts-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "banks-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("bankProducer"),
                        }
                )
        }
)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private ClientType type;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            mappedBy = "owner",
            fetch = FetchType.LAZY
    )
    private Set<BankAccount> bankAccounts;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "sender"
    )
    private Set<Transaction> sentTransactions;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY,
            mappedBy = "receiver"
    )
    private Set<Transaction> receivedTransactions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", name='" + name
                + '\'' + ", type=" + type
                + '}';
    }
}
