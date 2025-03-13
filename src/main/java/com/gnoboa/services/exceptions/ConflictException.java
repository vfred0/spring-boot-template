package com.gnoboa.services.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(MessageException messageException) {
        super(messageException.getMessage());
    }
}
