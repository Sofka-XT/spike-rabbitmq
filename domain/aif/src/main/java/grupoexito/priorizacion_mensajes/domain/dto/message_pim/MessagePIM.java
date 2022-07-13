package grupoexito.priorizacion_mensajes.domain.dto.message_pim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessagePIM {

    private HeaderPim header;
    private DataPim data;
}
