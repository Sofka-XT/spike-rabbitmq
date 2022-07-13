package grupoexito.priorizacion_mensajes.domain.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Canonical<T> {

    private final Header header;
    private final T data;
}
