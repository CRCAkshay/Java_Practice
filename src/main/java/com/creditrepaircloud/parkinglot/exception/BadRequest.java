package com.creditrepaircloud.parkinglot.exception;

public class BadRequest  extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 2261289236330857492L;

    public BadRequest(String message) {
        super(message);
    }
}
