package grupoexito.priorizacion_mensajes.web;

import grupoexito.priorizacion_mensajes.controller.MessageController;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.MessagePIM;
import grupoexito.priorizacion_mensajes.domain.dto.ms.MessageFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
@Log
public class MessageService {

    private final MessageFactory messageFactory;
    private final MessageController messageController;

    @PostMapping(path = "/api/send_message")
    public Mono<ResponseEntity> sendMessage(@RequestBody MessagePIM messagePIM){
        try {
            return messageFactory.mapMsDTO(messagePIM)
                    .flatMap(messagePIMCanonical -> messageController.sendMessage(messagePIMCanonical))
                            .flatMap(canonico -> Mono.just(ResponseEntity.status(HttpStatus.OK).body(canonico)));
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ha ocurrido un error al procesar el mensaje " + e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ha ocurrido un error al procesar el mensaje " + e));
        }
    }

}
