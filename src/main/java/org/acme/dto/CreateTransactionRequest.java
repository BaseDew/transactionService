package org.acme.dto;

public record CreateTransactionRequest(
        String accountId,
        String funds,
        String feeFactor,
        String recipient,
        String currency
) {
}
