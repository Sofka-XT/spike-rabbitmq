package grupoexito.priorizacion_mensajes.domain.transaction.elk;

import grupoexito.priorizacion_mensajes.domain.common.Event;
import reactor.core.publisher.Mono;

public interface ElkEventsGateway {

    Mono<Void> emit(Event event, Integer priority);
}
