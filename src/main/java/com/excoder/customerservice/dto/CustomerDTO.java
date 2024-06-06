package com.excoder.customerservice.dto;

import com.excoder.customerservice.model.Order;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long customerId;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
    private List<Order> orders;
}
