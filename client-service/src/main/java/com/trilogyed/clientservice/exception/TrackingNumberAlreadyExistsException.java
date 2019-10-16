package com.trilogyed.clientservice.exception;

public class TrackingNumberAlreadyExistsException extends RuntimeException {
    public TrackingNumberAlreadyExistsException() {
    }

    public TrackingNumberAlreadyExistsException(String message) {
        super(message);
    }
}
