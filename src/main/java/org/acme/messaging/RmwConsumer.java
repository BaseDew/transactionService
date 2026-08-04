package org.acme.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import io.vertx.core.buffer.Buffer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;



@ApplicationScoped
@Slf4j
public class RmwConsumer {

    @Incoming("transactions")
    public void process(Buffer msg) {
        String payload = msg.toString();
        log.info("Received message: {}", payload);
    }
}
