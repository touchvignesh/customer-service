package com.excoder.customerservice.service;

import com.excoder.customerservice.model.Customer;
import com.excoder.customerservice.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String customerTopic;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        customerRepository.save(customer);
        var successMessage = kafkaTemplate.send(customerTopic, customer);
        successMessage.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                successMessage.completeExceptionally(exception);
            } else {
                successMessage.complete(sendResult);
            }
            log.info(String.valueOf(sendResult));
        });
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerFromDB = customerRepository.findById(customer.getCustomerId());
        if (customerFromDB.isPresent()) {
            Customer customerFromDBVal = customerFromDB.get();
            customerFromDBVal.setOrders(customer.getOrders());
            customerFromDBVal.setCustomerId(customer.getCustomerId());
            customerFromDBVal.setFirstName(customer.getFirstName());
            customerFromDBVal.setLastName(customer.getLastName());
            customerFromDBVal.setCreatedDate(customer.getCreatedDate());
            customerRepository.save(customerFromDBVal);
            var successMessage = kafkaTemplate.send(customerTopic, customer);
            successMessage.whenComplete((sendResult, exception) -> {
                if (exception != null) {
                    successMessage.completeExceptionally(exception);
                } else {
                    successMessage.complete(sendResult);
                }
                log.info(String.valueOf(sendResult));
            });
        } else {
            return null;
        }
        return customer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
