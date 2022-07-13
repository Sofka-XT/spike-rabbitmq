package grupoexito.priorizacion_mensajes.controller;

import com.google.gson.Gson;
import grupoexito.domain.canonico.Canonico;
import grupoexito.priorizacion_mensajes.domain.common.UniqueIDGenerator;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.DataPim;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.MessagePIM;
import grupoexito.priorizacion_mensajes.domain.transaction.Canonical;
import grupoexito.priorizacion_mensajes.domain.transaction.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
public class MessageController {

    private final MessageSender messageSender;

    public Mono<DataPim> sendMessage(Canonical<MessagePIM> msCanonical) {
        Canonical canonical = Canonical.builder()
                .header(Header.builder()
                            .transactionId(msCanonical.getHeader().getTransactionId())
                            .transactionDate(new Date().toString())
                            .serviceName("priorizacion-mensajes")
                        .build())
                .data("TEST DATA")
                .build();

        return messageSender.sendMessage(msCanonical.getData(), msCanonical.getHeader().getPriority());
    }
}
