package com.prokarma.assignment.publisher.customer.service;

import com.prokarma.assignment.publisher.customer.domain.CustomerRequest;
import com.prokarma.assignment.publisher.customer.domain.CustomerResponse;

public interface CustomerPublisherService {

    public CustomerResponse publishCustomerRequest(CustomerRequest customerRequest);

}
