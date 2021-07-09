package com.prokarma.assignment.publisher.customer.exception;

import java.util.Map;
import java.util.TreeSet;

public class FieldErrorResponse {
    private String message = "The request is invalid.";
    private Map<String, TreeSet<String>> modelState;

    public Map<String, TreeSet<String>> getModelState() {
        return modelState;
    }

    public void setModelState(Map<String, TreeSet<String>> modelState) {
        this.modelState = modelState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
