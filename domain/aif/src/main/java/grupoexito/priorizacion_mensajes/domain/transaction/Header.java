package grupoexito.priorizacion_mensajes.domain.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Header {
    private String transactionId;
    private String serviceName;
    private String transactionDate;
    private Integer priority;
}
