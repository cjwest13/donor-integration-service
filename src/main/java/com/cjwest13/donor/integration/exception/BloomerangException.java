package com.cjwest13.donor.integration.exception;

public class BloomerangException extends Exception {

    public BloomerangException(String errorMessage) {
        super(errorMessage);
    }

    public BloomerangException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

}
