package grupoexito.priorizacion_mensajes.domain.transaction.elk;

import grupoexito.controller.ObjectToJson;
import grupoexito.priorizacion_mensajes.domain.dto.message_pim.DataPim;

import java.util.UUID;

public interface TransactionFactory extends ObjectToJson {

    default Transaction buildTransaction(String name, DataPim data) {
        return Transaction.builder()
                .data(data)
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
    }

}