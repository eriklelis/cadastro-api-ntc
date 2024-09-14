package com.hoteis.api_cadastro.exception;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LogManager.getLogger(ApplicationExceptionHandler.class);

    private final MessageSource messageSource;

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
            WebRequest request) {
        List<ErrorDTO> errors = getErrors("resource.not-found", HttpStatus.NOT_FOUND);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        List<ErrorDTO> errors = getErrors(ex.getMessage(), HttpStatus.NOT_FOUND);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        List<ErrorDTO> errors = getErrors("resource.operation-not-allowed", HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ ValidacaoException.class })
    public ResponseEntity<Object> handleValidationException(ValidacaoException ex, WebRequest request) {
        log.error(ex.getMessage(), ex.getArgs());
        return handleExceptionInternal(ex,
                getError(HttpStatus.BAD_REQUEST, getMessage(ex.getMessageExceptions(), ex.getArgs())),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String getMessage(String messageExceptions, Object[] args) {
        return String.format(messageExceptions, args);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<ErrorDTO> errors = new ArrayList<>();
        List<String> errorsString = new ArrayList<>();

        for (var erro : ex.getBindingResult().getFieldErrors()) {
            log.error(erro);
        }

        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @SuppressWarnings("removal")
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,
                                                         HttpHeaders headers,
                                                         HttpStatusCode status,
                                                         WebRequest request) {
        List<ErrorDTO> errors = getErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        List<ErrorDTO> errors = getErrors("resource.access-denied", HttpStatus.FORBIDDEN);
        return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers,
                    HttpStatus.valueOf(status.value()), request);
        }

        var error = getError(HttpStatus.valueOf(status.value()), "Request body invalido");

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        var error = getError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(error);
    }

    private ErrorDTO getError(HttpStatus status, String message) {
        return ErrorDTO.builder().code(status).message(message).timestamp(Instant.now()).build();
    }

    private String returnValidationError(String msg, int i) {
        if (msg.contains("|")) {
            return msg.split("\\|")[i];
        }
        return msg;
    }

    private List<ErrorDTO> getErrors(String message, HttpStatus status) {
        return Collections.singletonList(getError(status, message));
    }

    private List<ErrorDTO> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().map(fieldError -> {
            var msg = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return returnValidationError(msg, 0);
        }).map(msg -> ErrorDTO.builder().code(HttpStatus.BAD_REQUEST).message(msg).timestamp(Instant.now()).build())
                .toList();
    }

    private String joinPath(List<Reference> references) {
        return references.stream().map(Reference::getFieldName).collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());
        var error = getError(status, String.format("Property '%s' is invalid.", path));
        return handleExceptionInternal(ex, error, headers, status, request);
    }
}
