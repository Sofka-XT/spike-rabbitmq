package grupoexito.priorizacion_mensajes.reactive.events;

import grupoexito.priorizacion_mensajes.domain.common.Event;
import grupoexito.priorizacion_mensajes.domain.common.EventsGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.async.impl.config.annotations.EnableDomainEventBus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static reactor.core.publisher.Mono.from;

@Log
@Component
@EnableDomainEventBus
@RequiredArgsConstructor
public class ReactiveEventsGateway implements EventsGateway {

    private final DomainEventBus domainEventBus;

    @Override
    public Mono<Void> emit(Event event) {
        return from(domainEventBus.emit(new DomainEvent<>(event.name(), UUID.randomUUID().toString(), event.getData())));
    }
}

