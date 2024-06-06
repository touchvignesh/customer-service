package com.excoder.customerservice.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

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
