package com.hoteis.api_cadastro.exception;


public class NaoAutorizadoException extends GenericException {

    public NaoAutorizadoException() {
        super("Erro de validação");
    }

    public NaoAutorizadoException(String messageExceptions, Object... args) {
        super(messageExceptions, args);
    }

    public NaoAutorizadoException(String messageExceptions) {
        super(messageExceptions, null);
    }

}
