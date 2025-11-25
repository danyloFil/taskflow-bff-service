package com.taskflow.taskflow_bff_service.infrastructure.exception;


import javax.security.sasl.AuthenticationException;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
