package com.excoder.customerservice.controller;

import com.excoder.customerservice.model.Customer;
import com.excoder.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // find all customers
    @GetMapping("/list/all")
    public List<Customer> findAll() {
        return customerService.getAllCustomers();
    }

    // create a new customer
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // update a customer
    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    // delete a customer
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
