package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.acme.dto.CreateTransactionRequest;
import org.acme.dto.TransactionDto;
import org.acme.mapper.TransactionMapper;
import org.acme.messaging.in.RmqProducer;
import org.acme.repository.TransactionEntity;
import org.acme.repository.TransactionRepository;
import org.acme.repository.TransactionStatus;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final RmqProducer rmqProducer;

    public List<TransactionEntity> getAllAccountTransactions(String accountId) {
        return transactionRepository.findbyUserId(accountId);
    }

    public TransactionEntity getTransactionById(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }


    /**
     * Example data for json
     *  {
     *   "accountId": "2",
     *   "funds": "1000.00",
     *   "feeFactor": "0.02",
     *   "recipient": "John Doe",
     *   "currency": "PLN"
     * }
     */
    @Transactional
    public TransactionDto createTransactionAndDeductFunds(CreateTransactionRequest transactionRequest) {
        TransactionEntity transactionEntity = transactionMapper.toEntity(transactionRequest);
        transactionEntity.setTimestamp(String.valueOf(System.currentTimeMillis()));
        transactionEntity.setTransactionStatus(TransactionStatus.PENDING);
        transactionRepository.persist(transactionEntity);
        rmqProducer.send(transactionMapper.toDto(transactionEntity));
        return transactionMapper.toDto(transactionEntity);
    }

    @Transactional
    public void updateTransactionStatus(String transactionId, String newStatus) {
        TransactionEntity transaction = transactionRepository.findByTransactionId(transactionId);
        if (transaction != null) {
            transaction.transactionStatus = TransactionStatus.valueOf(newStatus);
            transactionRepository.persist(transaction);
        }
    }


}
