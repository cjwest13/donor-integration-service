package com.cjwest13.donor.integration.exception;

public class ActBlueException extends Exception {

    public ActBlueException(String errorMessage) {
        super(errorMessage);
    }

    public ActBlueException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}
