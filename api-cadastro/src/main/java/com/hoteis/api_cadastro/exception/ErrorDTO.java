package com.hoteis.api_cadastro.exception;

import java.time.Instant;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorDTO {

    private final HttpStatus code;
    private final String message;
    private final Object[] messageParameters;
    @Default
    private final Instant timestamp = Instant.now();

}
