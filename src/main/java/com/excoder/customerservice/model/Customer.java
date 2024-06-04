package com.excoder.customerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Customer")
public class Customer {
    @Id
    @Property("customer_id")
    private Long customerId;

    @Property("firstname")
    private String firstName;

    @Property("lastname")
    private String lastName;

    @Property("created_date")
    private LocalDate createdDate;

    @Relationship(type = "ORDERED")
    private List<Order> orders;

}

