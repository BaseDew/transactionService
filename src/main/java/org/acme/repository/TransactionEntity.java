package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class TransactionEntity extends PanacheEntity {

    @Column(name = "account_id", nullable = false)
    public String accountId;

    @Column(name = "funds", nullable = false)
    public String funds;

    @Column(name = "fee_factor")
    public String feeFactor;

    @Column(name = "recipient", nullable = false)
    public String recipient;

    @Column(name = "currency", nullable = false)
    public String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    public TransactionStatus transactionStatus;

    @Column(name = "timestamp", nullable = false)
    public String timestamp;
}
