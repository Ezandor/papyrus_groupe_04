package com.sci.papyrus.common;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException() {
        super();
    }

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(Throwable throwable) {
        super(throwable);
    }

    public DuplicateResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
