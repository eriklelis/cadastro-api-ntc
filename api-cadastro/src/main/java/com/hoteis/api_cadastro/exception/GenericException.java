package com.hoteis.api_cadastro.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private static final long serialVersionUID = -6727851438822539329L;

    private final String messageExceptions;
    private final Object[] args;

    public GenericException(String messageExceptions, Object... args) {
        this.messageExceptions = messageExceptions;
        this.args = args;
    }
}
