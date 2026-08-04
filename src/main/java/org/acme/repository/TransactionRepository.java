package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<TransactionEntity> {

    public List<TransactionEntity> findbyUserId(String accountId) {
        return find("accountId", accountId).list();
    }

    public TransactionEntity findByTransactionId(String transactionId) {
        return find("transactionId", transactionId).firstResult();
    }
}
