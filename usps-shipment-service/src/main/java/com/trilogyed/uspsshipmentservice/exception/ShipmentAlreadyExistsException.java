package com.trilogyed.uspsshipmentservice.exception;

public class ShipmentAlreadyExistsException extends RuntimeException {
    public ShipmentAlreadyExistsException() {
    }

    public ShipmentAlreadyExistsException(String message) {
        super(message);
    }
}
