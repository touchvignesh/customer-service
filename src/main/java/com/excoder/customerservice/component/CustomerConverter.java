package com.excoder.customerservice.component;

import com.excoder.customerservice.dto.CustomerDTO;
import com.excoder.customerservice.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public CustomerDTO convertCustomerEntityToDto(Customer customer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer convertCustomerDtoToEntity(CustomerDTO customerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerDTO, Customer.class);
    }
}
