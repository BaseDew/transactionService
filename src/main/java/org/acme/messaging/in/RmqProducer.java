package org.acme.messaging.in;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dto.TransactionDto;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class RmqProducer {

    @Inject
    @Channel("transactions-out")
    Emitter<TransactionDto> emitter;

    public void send(TransactionDto transactionDto) {
        emitter.send(transactionDto);
    }
}
