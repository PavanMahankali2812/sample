package com.prokarma.assignment.publisher.customer.exception;

public class RequestValidationException extends RuntimeException {
    private static final long serialVersionUID = -9031394117485559541L;

    public RequestValidationException(String statusMessage) {
        super(statusMessage);
    }

}
