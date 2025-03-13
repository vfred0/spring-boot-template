package com.gnoboa.services.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(MessageException messageException) {
        super(messageException.getMessage());
    }

}
