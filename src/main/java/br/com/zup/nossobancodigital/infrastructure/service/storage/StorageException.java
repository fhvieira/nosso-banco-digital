package br.com.zup.nossobancodigital.infrastructure.service.storage;

public class StorageException extends RuntimeException {
    private final static Long serialVersionUID = 1L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
