package com.gnoboa.services.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(MessageException messageException) {
        super(messageException.getMessage());
    }
}
