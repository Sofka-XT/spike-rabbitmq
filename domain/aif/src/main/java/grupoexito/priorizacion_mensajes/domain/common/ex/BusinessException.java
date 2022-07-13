package grupoexito.priorizacion_mensajes.domain.common.ex;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException {

    public enum Type {
        INVALID_TODO_INITIAL_DATA("Invalid TODO initial data"),
        TASK_NOT_FOUND("Indicated Task not found!"),
        USER_NOT_EXIST("Indicated User not exist!"),
        TASK_NOT_ASSIGNED("Task has not been assigned!"),
        TASK_ALREADY_ASSIGNED("Task already assigned!");

        private final String message;

        public String getMessage() {
            return message;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;

    public BusinessException(Type type){
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode(){
        return type.name();
    }


}
