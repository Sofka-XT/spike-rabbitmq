package grupoexito.priorizacion_mensajes.reactive.events;

import com.google.gson.Gson;
import grupoexito.priorizacion_mensajes.domain.common.Event;
import grupoexito.priorizacion_mensajes.domain.transaction.elk.ElkEventsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.logging.Level;

import static reactor.core.publisher.Mono.from;

@Log
@Component
@RequiredArgsConstructor
public class CustomReactiveEventsGateway implements ElkEventsGateway {

    @Value("${app.async.topic.exchange}")
    private String elkExchange;

    private final CustomReactiveMessageSender customReactiveMessageSender;

    @Override
    public Mono<Void> emit(Event event, Integer priority) {

        log.log(Level.INFO, "Emitting message to RabbitMQ with priority " + priority);
        log.log(Level.INFO, new Gson().toJson(event));

        return from(customReactiveMessageSender.sendWithConfirm(event.getData(), elkExchange, event.name(),
                Collections.emptyMap(), priority)
                .onErrorMap(err -> new RuntimeException("Event send failure: " + event.name(), err)));
    }
}
