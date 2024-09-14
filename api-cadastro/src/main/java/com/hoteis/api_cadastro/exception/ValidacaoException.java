package com.hoteis.api_cadastro.exception;


public class ValidacaoException extends GenericException {

    public ValidacaoException() {
        super("Erro de validação");
    }

    public ValidacaoException(String messageExceptions, Object... args) {
        super(messageExceptions, args);
    }

    public ValidacaoException(String messageExceptions) {
        super(messageExceptions, null);
    }

}
