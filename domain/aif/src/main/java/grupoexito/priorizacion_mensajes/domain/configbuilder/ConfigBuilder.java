package grupoexito.priorizacion_mensajes.domain.configbuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ConfigBuilder {
    private final Integer elementPerPages;
}
