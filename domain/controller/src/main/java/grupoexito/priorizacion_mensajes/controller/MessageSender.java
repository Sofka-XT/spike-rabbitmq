package grupoexito.priorizacion_mensajes.controller;

import grupoexito.domain.canonico.Canonico;
import grupoexito.domain.elk.Message;
import grupoexito.domain.elk.TransactionInformation;
import grupoexito.priorizacion_mensajes.domain.canonico.CanonicoFactory;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.DataPim;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.MessagePIM;
import grupoexito.priorizacion_mensajes.domain.transaction.Canonical;
import grupoexito.priorizacion_mensajes.domain.transaction.elk.ElkEventsGateway;
import grupoexito.priorizacion_mensajes.domain.transaction.elk.TransactionEvent;
import grupoexito.priorizacion_mensajes.domain.transaction.elk.TransactionFactory;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.logging.Level;

@Log
@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class MessageSender implements TransactionFactory, CanonicoFactory {

    private final ElkEventsGateway elkEventsGateway;
    private final String integrationName;
    private final String domainName;
    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String LOG_MESSAGE = "Error sending message to RabbitMQ. Error:{0}";
    private static final String IN = "IN";
    private static final String OUT = "OUT";
    public static final String EVENT_NAME = "priorizacion.mensajes.json";

    public Mono<DataPim> sendMessage(MessagePIM message, Integer priority) {
        return emitMessageToRabbitMQ( message.getData(), priority);
    }

    private Mono<DataPim> emitMessageToRabbitMQ(DataPim data, Integer priority) {
        data.setTransactionDate(new Date());
        data.setPriority(priority);

        return elkEventsGateway.emit(new TransactionEvent(buildTransaction(EVENT_NAME, data)), priority)
                .onErrorResume(error -> {
                    log.log(Level.WARNING, LOG_MESSAGE, error.toString());
                    return Mono.empty();
                })
                .thenReturn(data);
    }

}
