package com.trilogyed.bookservice.exception;

public class NotFoundException extends Throwable {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
