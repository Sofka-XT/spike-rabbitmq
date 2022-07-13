package grupoexito.priorizacion_mensajes;

import com.rabbitmq.client.ConnectionFactory;
import grupoexito.priorizacion_mensajes.reactive.events.CustomReactiveMessageSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivecommons.async.impl.communications.ReactiveMessageSender;
import org.reactivecommons.async.impl.config.ConnectionFactoryProvider;
import org.reactivecommons.async.impl.config.RabbitProperties;
import org.reactivecommons.async.impl.config.props.AsyncProps;
import org.reactivecommons.async.impl.config.props.BrokerConfigProps;
import org.reactivecommons.async.impl.converters.MessageConverter;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RabbitMqConfigTest {

    private RabbitMqConfig rabbitMqConfig;

    @Mock
    private RabbitProperties rabbitProperties;

    @Mock
    private ConnectionFactoryProvider provider;

    @Mock
    private MessageConverter converter;

    @Mock
    private BrokerConfigProps brokerConfigProps;

    @Mock
    private AsyncProps asyncProps;

    @Before
    public void setup() {
        rabbitMqConfig = new RabbitMqConfig(asyncProps);

        ReflectionTestUtils.setField(rabbitMqConfig, "appName", "1");
        ReflectionTestUtils.setField(rabbitMqConfig, "elkHost", "1");
        ReflectionTestUtils.setField(rabbitMqConfig, "elkUserName", "1");
        ReflectionTestUtils.setField(rabbitMqConfig, "elkPassword", "1");
        ReflectionTestUtils.setField(rabbitMqConfig, "elkVH", "1");
        ReflectionTestUtils.setField(rabbitMqConfig, "elkPort", 1);
        ReflectionTestUtils.setField(rabbitMqConfig, "tlsVersion", "TLSv1.1");
    }

    @Test
    public void appConnectionFactory() throws NoSuchAlgorithmException, KeyManagementException {
        assertThat(rabbitMqConfig.appConnectionFactoryNew(rabbitProperties))
                .isInstanceOf(ConnectionFactoryProvider.class);
    }

    @Test
    public void appConnectionFactoryElk() throws NoSuchAlgorithmException, KeyManagementException {
        assertThat(rabbitMqConfig.appConnectionFactoryELK(rabbitProperties))
                .isInstanceOf(ConnectionFactoryProvider.class);
    }

    @Test
    public void customReactiveSender() {
        ReflectionTestUtils.setField(rabbitMqConfig, "appName", "TEST");

        Mockito.when(provider.getConnectionFactory()).thenReturn(new ConnectionFactory());
        Mockito.when(rabbitProperties.getCache()).thenReturn(new RabbitProperties.Cache());
        Mockito.when(brokerConfigProps.getAppName()).thenReturn("TEST");

        assertThat(rabbitMqConfig.customReactiveSender(provider, converter, brokerConfigProps, rabbitProperties))
                .isInstanceOf(CustomReactiveMessageSender.class);
    }

    @Test
    public void reactiveSender() {
        ReflectionTestUtils.setField(rabbitMqConfig, "appName", "TEST");

        Mockito.when(provider.getConnectionFactory()).thenReturn(new ConnectionFactory());
        Mockito.when(rabbitProperties.getCache()).thenReturn(new RabbitProperties.Cache());
        Mockito.when(brokerConfigProps.getAppName()).thenReturn("TEST");

        assertThat(rabbitMqConfig.reactiveSender(provider, converter, brokerConfigProps, rabbitProperties))
                .isInstanceOf(ReactiveMessageSender.class);
    }
}