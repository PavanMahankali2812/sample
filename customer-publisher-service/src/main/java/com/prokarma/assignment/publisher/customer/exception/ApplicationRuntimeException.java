package com.prokarma.assignment.publisher.customer.exception;

public class ApplicationRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ApplicationRuntimeException(String statusMessage) {
        super(statusMessage);
    }

}
