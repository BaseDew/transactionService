package org.acme.dto;

import org.acme.repository.TransactionStatus;

public record TransactionDto(
        String accountId,
        String funds,
        String feeFactor,
        String recipient,
        String currency,
        TransactionStatus transactionStatus,
        String timestamp
) {
}
