package grupoexito.priorizacion_mensajes.domain.dto.message_pim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HeaderPim {

    private String transactionId;
    private String serviceName;
    private Date transactionDate;
    private String messageErrorMapper;
    private Integer priority;

}
