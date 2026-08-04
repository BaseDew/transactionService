package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.acme.dto.CreateTransactionRequest;
import org.acme.dto.TransactionDto;
import org.acme.mapper.TransactionMapper;
import org.acme.repository.TransactionEntity;
import org.acme.repository.TransactionRepository;
import org.acme.repository.TransactionStatus;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionEntity> getAllAccountTransactions(String accountId) {
        return transactionRepository.findbyUserId(accountId);
    }

    public TransactionEntity getTransactionById(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    public TransactionDto createTransactionAndDeductFunds(CreateTransactionRequest transactionRequest) {
        // Implement the logic to create a transaction and deduct funds from the account
        // This is a placeholder for the actual implementation
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionRequest);
        transactionRepository.persist(transactionEntity);
        return transactionMapper.toDto(transactionEntity);
    }

    public void updateTransactionStatus(String transactionId, String newStatus) {
        TransactionEntity transaction = transactionRepository.findByTransactionId(transactionId);
        if (transaction != null) {
            transaction.transactionStatus = TransactionStatus.valueOf(newStatus);
            transactionRepository.persist(transaction);
        }
    }


}
