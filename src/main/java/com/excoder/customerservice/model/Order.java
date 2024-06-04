package com.excoder.customerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Order")
public class Order {
    @Id
    @Property("order_id")
    private Integer orderId;

    @Property("order_total")
    private BigDecimal total;

    @Property("created_date")
    private LocalDate createdDate;
}

