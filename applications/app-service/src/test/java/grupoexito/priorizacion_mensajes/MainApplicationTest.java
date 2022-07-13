package grupoexito.priorizacion_mensajes;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MainApplicationTest {

    MainApplication mainApplication;

    @Test
    public void correctExecution(){
        mainApplication = new MainApplication();

        Assertions.assertThat(mainApplication).isNotNull();
    }

}