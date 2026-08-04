package org.acme.mapper;

import org.acme.dto.CreateTransactionRequest;
import org.acme.dto.TransactionDto;
import org.acme.repository.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactionStatus", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    TransactionEntity toEntity(CreateTransactionRequest request);

    TransactionDto toDto(TransactionEntity entity);
}
