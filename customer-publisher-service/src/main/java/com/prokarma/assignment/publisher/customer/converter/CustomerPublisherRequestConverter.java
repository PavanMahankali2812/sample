package com.prokarma.assignment.publisher.customer.converter;

public interface CustomerPublisherRequestConverter<I, O> {
    O convert(I input);
}
