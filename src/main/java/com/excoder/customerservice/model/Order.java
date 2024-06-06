package com.excoder.customerservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

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
