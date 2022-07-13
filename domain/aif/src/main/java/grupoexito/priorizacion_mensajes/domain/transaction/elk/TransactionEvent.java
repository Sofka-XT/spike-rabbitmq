package grupoexito.priorizacion_mensajes.domain.transaction.elk;

import grupoexito.priorizacion_mensajes.domain.common.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class TransactionEvent implements Event {

    public static final String EVENT_NAME = "priorizacion.mensajes.json";
    private final Transaction transactionElk;

    @Override
    public String name() {
        return EVENT_NAME;
    }

    @Override
    public Object getData() {
        return this.transactionElk;
    }
}
