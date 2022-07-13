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
public class DataPim {

    private String content;
    private Date transactionDate;
    private String transactionId;
    private Integer priority;
}
