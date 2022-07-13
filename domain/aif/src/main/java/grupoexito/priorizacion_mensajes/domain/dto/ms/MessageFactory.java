package grupoexito.priorizacion_mensajes.domain.dto.ms;

import com.google.gson.Gson;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.MessagePIM;
import grupoexito.priorizacion_mensajes.domain.transaction.Canonical;
import grupoexito.priorizacion_mensajes.domain.transaction.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Log
public class MessageFactory {

    private final ObjectMapper objectMapper;

    public Mono<Canonical<MessagePIM>> mapMsDTO(MessagePIM messagePIM) {

        if (!isNull(messagePIM.getHeader().getMessageErrorMapper())){
            log.log(Level.SEVERE, new Gson().toJson(messagePIM));
            return null;
        }

        try {
            final Header header = mapHeader(messagePIM);
            return Mono.just(buildCanonical(header, messagePIM));
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }


    private Header mapHeader(MessagePIM messagePIM) {
        return objectMapper.mapBuilder(messagePIM.getHeader(),
                Header.HeaderBuilder.class).build();
    }


    private Canonical<MessagePIM> buildCanonical(Header header, MessagePIM data) {
        return Canonical.<MessagePIM>builder()
                .header(header)
                .data(data)
                .build();
    }
}
