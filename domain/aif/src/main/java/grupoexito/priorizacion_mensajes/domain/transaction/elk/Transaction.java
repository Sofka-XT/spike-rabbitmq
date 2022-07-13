package grupoexito.priorizacion_mensajes.domain.transaction.elk;

import grupoexito.domain.elk.Event;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.DataPim;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.MessagePIM;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Transaction<T> implements Serializable {
    private final String name;
    private final String id;
    private final DataPim data;

}

