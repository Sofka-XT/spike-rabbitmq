package grupoexito.priorizacion_mensajes.domain.common;

public class ErrorEnum {

    public enum Type {
        EXITOSO("Ejecuci\u00F3n exitosa", "0"),
        EXITOSO_SIN_DATOS("Ejecucion exitosa sin datos retornados", "2"),
        ERROR_TECNICO("Error t\u00E9cnico", "3"),
        ERROR_PARAMETROS_ENTRADA("Error en parametros de entrada", "4"),
        ERROR_NEGOCIO("Error de negocio", "5");

        private String message;
        private String code;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }

        Type(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }

}
