package com.prokarma.assignment.publisher.customer.service;

import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.prokarma.assignment.publisher.customer.converter.DefaultCustomerPublisherRequestConverter;
import com.prokarma.assignment.publisher.customer.domain.CustomerRequest;
import com.prokarma.assignment.publisher.customer.domain.CustomerResponse;
import com.prokarma.assignment.publisher.customer.exception.ApplicationRuntimeException;
import com.prokarma.assignment.publisher.customer.kafka.domain.KafkaCustomerRequest;

@Service
public class DefaultCustomerPublisherService implements CustomerPublisherService {

    Logger logger = LoggerFactory.getLogger(DefaultCustomerPublisherService.class);

    @Autowired
    private KafkaTemplate<String, KafkaCustomerRequest> kafkaTemplate;

    @Value("${cloudkarafka.topic}")
    private String topic;
    
    @Autowired
    private DefaultCustomerPublisherRequestConverter defaultCustomerRequestConverter;


    @Override
    public CustomerResponse publishCustomerRequest(CustomerRequest customerRequest) {

        CustomerResponse response = null;
        long startingTime = System.currentTimeMillis();

        try {
        	 KafkaCustomerRequest kafkaCustomerRequest =
                     defaultCustomerRequestConverter.convert(customerRequest);

             KafkaCustomerRequest maskedKafkaCustomerRequest =
                     defaultCustomerRequestConverter.maskKafkaCustomerRequest(kafkaCustomerRequest);
           
            logger.info("Masked Kafaka Customer Request : {}", maskedKafkaCustomerRequest);
            
            kafkaTemplate.send(topic, kafkaCustomerRequest);

            logger.info("Data published in time : {} ms",
                    System.currentTimeMillis() - startingTime);

            response = new CustomerResponse();
            response.setStatus("success");
            response.setMessage("Successfully published data");

        } catch (TimeoutException e) {
            throw new ApplicationRuntimeException("Error in publishing data");
        }

        return response;
    }

}
