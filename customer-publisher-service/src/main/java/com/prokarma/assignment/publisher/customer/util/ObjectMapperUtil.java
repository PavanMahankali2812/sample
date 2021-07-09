package com.prokarma.assignment.publisher.customer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.assignment.publisher.customer.exception.ApplicationRuntimeException;

public class ObjectMapperUtil {

    private ObjectMapperUtil() {
        throw new AssertionError("No Object Creation");
    }

    public static String returnJsonFromObject(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            throw new ApplicationRuntimeException("Json processing exception" + ex);
        }
    }
}
