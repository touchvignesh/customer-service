package com.excoder.customerservice.controller;

import com.excoder.customerservice.dto.CustomerDTO;
import com.excoder.customerservice.model.Customer;
import com.excoder.customerservice.service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    // find customer by customer Id
    @GetMapping("/list/id/{id}")
    public Optional<Customer> findCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // create a new customer
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/create")
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        return customerService.addCustomer(customerDTO);
    }

    // update a customer
    @PutMapping("/update")
    public CustomerDTO update(@RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerDTO);
    }

    // delete a customer
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
