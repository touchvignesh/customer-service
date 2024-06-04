package com.excoder.customerservice.service;

import com.excoder.customerservice.model.Customer;
import com.excoder.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String customerTopic;

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer){
        customerRepository.save(customer);
        var message_success = kafkaTemplate.send(customerTopic, customer);
        message_success.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                message_success.completeExceptionally(exception);
            } else {
                message_success.complete(sendResult);
            }
            System.out.println(sendResult);
        });
        return customer;

    }

    public Customer updateCustomer(Customer customer)  {
        Optional<Customer> customerFromDB= customerRepository.findById(customer.getCustomerId());
        if(customerFromDB.isPresent()){
            Customer customerFromDBVal = customerFromDB.get();
            customerFromDBVal.setOrders(customer.getOrders());
            customerFromDBVal.setCustomerId(customer.getCustomerId());
            customerFromDBVal.setFirstName(customer.getFirstName());
            customerFromDBVal.setLastName(customer.getLastName());
            customerFromDBVal.setCreatedDate(customer.getCreatedDate());
            customerRepository.save(customerFromDBVal);
            var message_success = kafkaTemplate.send(customerTopic, customer);
            message_success.whenComplete((sendResult, exception) -> {
                if (exception != null) {
                    message_success.completeExceptionally(exception);
                } else {
                    message_success.complete(sendResult);
                }
                System.out.println(sendResult);
            });
        }else{
            return null;
        }
        return customer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
