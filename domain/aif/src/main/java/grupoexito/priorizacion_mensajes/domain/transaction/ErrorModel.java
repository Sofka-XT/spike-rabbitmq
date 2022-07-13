package grupoexito.priorizacion_mensajes.domain.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ErrorModel {

    private final String code;
    private final String type;
    private final String description;
}
