package grupoexito.priorizacion_mensajes.reactive.events;

import com.rabbitmq.client.AMQP;
import org.reactivecommons.async.impl.communications.Message;
import org.reactivecommons.async.impl.communications.TopologyCreator;
import org.reactivecommons.async.impl.converters.MessageConverter;
import org.reactivecommons.async.impl.exceptions.SendFailureNoAckException;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.reactivecommons.async.impl.Headers.SOURCE_APPLICATION;
import static reactor.core.publisher.Mono.just;

public class CustomReactiveMessageSender {

    private final Sender sender;
    private final String sourceApplication;
    private final MessageConverter messageConverter;
    private final TopologyCreator topologyCreator;

    public CustomReactiveMessageSender(Sender sender, String sourceApplication, MessageConverter messageConverter,
                                       TopologyCreator topologyCreator) {
        this.sender = sender;
        this.sourceApplication = sourceApplication;
        this.messageConverter = messageConverter;
        this.topologyCreator = topologyCreator;
    }

    public <T> Mono<Void> sendWithConfirm(T message, String exchange, String routingKey, Map<String, Object> headers, Integer priority) {
        return just(toOutboundMessage(message, exchange, routingKey, headers, priority))
                .map(Mono::just)
                .flatMapMany(sender::sendWithPublishConfirms)
                .next()
                .flatMap(result -> result.isAck() ?
                        Mono.empty() :
                        Mono.error(new SendFailureNoAckException("Event no ACK in communications"))
                )
                .then();
    }

    private <T> OutboundMessage toOutboundMessage(T object, String exchange, String routingKey, Map<String, Object> headers, Integer priority) {
        final Message message = messageConverter.toMessage(object);
        final AMQP.BasicProperties props = buildMessageProperties(message, headers, priority);
        return new OutboundMessage(exchange, routingKey, props, message.getBody());
    }

    private AMQP.BasicProperties buildMessageProperties(Message message, Map<String, Object> headers, Integer priority) {
        final Message.Properties properties = message.getProperties();
        final Map<String, Object> baseHeaders = new HashMap<>(properties.getHeaders());
        baseHeaders.putAll(headers);
        baseHeaders.put(SOURCE_APPLICATION, sourceApplication);
        return new AMQP.BasicProperties.Builder()
                .contentType(properties.getContentType())
                .priority(priority)
                .appId(sourceApplication)
                .contentEncoding(properties.getContentEncoding())
                .deliveryMode(2)
                .timestamp(new Date())
                .messageId(UUID.randomUUID().toString())
                .headers(baseHeaders).build();
    }

    public Sender getSender() {
        return sender;
    }

    public TopologyCreator getTopologyCreator() {
        return topologyCreator;
    }
}
