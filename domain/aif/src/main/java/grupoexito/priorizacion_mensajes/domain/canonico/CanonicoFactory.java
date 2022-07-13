package grupoexito.priorizacion_mensajes.domain.canonico;

import grupoexito.domain.canonico.Canonico;
import grupoexito.domain.canonico.ErrorModel;
import grupoexito.domain.canonico.Header;
import grupoexito.priorizacion_mensajes.domain.common.ErrorEnum;
import grupoexito.priorizacion_mensajes.domain.transaction.Canonical;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public interface CanonicoFactory {

    default <T> Canonico<T> buildCanonicoMessage(Canonical<T> transaction, String status) {

        return Canonico.<T>builder()
                .header(Header.builder()
                        .transactionId(transaction.getHeader().getTransactionId())
                        .applicationId(transaction.getHeader().getServiceName())
                        .hostname(transaction.getHeader().getServiceName())
                        .user(transaction.getHeader().getServiceName())
                        .transactionDate(getTransactionDate(transaction.getHeader().getTransactionDate()))
                        .errors(Collections.singletonList(ErrorModel.builder()
                                .code(status.equals(Enum.OK.message) ?
                                        ErrorEnum.Type.EXITOSO.getCode()
                                        : ErrorEnum.Type.ERROR_TECNICO.getCode())
                                .type(status.equals(Enum.OK.message) ?
                                        ErrorEnum.Type.EXITOSO.getMessage()
                                        : ErrorEnum.Type.ERROR_TECNICO.getMessage())
                                .build()))
                        .build()
                )
                .data(transaction.getData()).build();
    }

    default Date getTransactionDate(String dateString) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH)
                    .parse(dateString);
        } catch (Exception e) {
            return new Date();
        }
    }

    default <T, E> Canonico<T> buildCanonicoMessage(Canonical<E> transaction, String status, T data) {

        return Canonico.<T>builder()
                .header(Header.builder()
                        .transactionId(transaction.getHeader().getTransactionId())
                        .applicationId(transaction.getHeader().getServiceName())
                        .hostname(transaction.getHeader().getServiceName())
                        .user(transaction.getHeader().getServiceName())
                        .transactionDate(getTransactionDate(transaction.getHeader().getTransactionDate()))
                        .errors(Collections.singletonList(ErrorModel.builder()
                                .code(status.equals(Enum.OK.message) ?
                                        ErrorEnum.Type.EXITOSO.getCode()
                                        : ErrorEnum.Type.ERROR_TECNICO.getCode())
                                .type(status.equals(Enum.OK.message) ?
                                        ErrorEnum.Type.EXITOSO.getMessage()
                                        : ErrorEnum.Type.ERROR_TECNICO.getMessage())
                                .build()))
                        .build()
                )
                .data(data)
                .build();
    }

    default <T, E> Canonico<T> buildCanonicoMessageBusinessError(Canonical<E> transaction, T data) {

        return Canonico.<T>builder()
                .header(Header.builder()
                        .transactionId(transaction.getHeader().getTransactionId())
                        .applicationId(transaction.getHeader().getServiceName())
                        .hostname(transaction.getHeader().getServiceName())
                        .user(transaction.getHeader().getServiceName())
                        .transactionDate(getTransactionDate(transaction.getHeader().getTransactionDate()))
                        .errors(Collections.singletonList(ErrorModel.builder()
                                .code(ErrorEnum.Type.ERROR_NEGOCIO.getCode())
                                .type(ErrorEnum.Type.ERROR_NEGOCIO.getMessage())
                                .build()))
                        .build()
                )
                .data(data)
                .build();
    }

    enum Enum {
        OK("OK");

        private String message;

        Enum(String message) {
            this.message = message;
        }

    }
}
