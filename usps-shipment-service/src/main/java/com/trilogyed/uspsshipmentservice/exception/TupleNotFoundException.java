package com.trilogyed.uspsshipmentservice.exception;

public class TupleNotFoundException extends RuntimeException {
    public TupleNotFoundException() {
    }

    public TupleNotFoundException(String message) {
        super(message);
    }
}
