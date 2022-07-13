package grupoexito.priorizacion_mensajes;

import grupoexito.priorizacion_mensajes.controller.MessageController;
import grupoexito.priorizacion_mensajes.controller.MessageSender;
import grupoexito.priorizacion_mensajes.domain.dto.ms.MessageFactory;
import grupoexito.priorizacion_mensajes.domain.transaction.elk.ElkEventsGateway;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Value("${domain.name}")
    private String domainName;

    @Value("${service}")
    private String service;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    public MessageFactory msBrandFactory(ObjectMapper objectMapper) {
        return new MessageFactory(objectMapper);
    }

    @Bean
    public MessageController msCanonicalController(ElkEventsGateway traceabilitySender) {

        return new MessageController(
                MessageSender.builder()
                        .elkEventsGateway(traceabilitySender)
                        .domainName(domainName)
                        .integrationName(service)
                        .build());
    }


}
