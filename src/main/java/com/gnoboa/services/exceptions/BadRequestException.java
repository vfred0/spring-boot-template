package com.gnoboa.services.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(MessageException messageException) {
        super(messageException.getMessage());
    }

}
