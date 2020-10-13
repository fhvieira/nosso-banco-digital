package br.com.zup.nossobancodigital.infrasctructure.service.email;

public class EmailException extends RuntimeException {
    private final static Long serialVersionUID = 1L;

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
