package by.ganevich.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "commissionForClients")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "bankId")
    private Bank bank;

    @Column(name = "clientType")
    private Integer clientType;

    @Column(name = "commission")
    private Double commission;

    @Override
    public String toString() {
        return "Commission{"
                + ", clientType=" + clientType
                + ", commission=" + commission
                + '}';
    }
}
