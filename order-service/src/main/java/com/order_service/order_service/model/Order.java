package com.order_service.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    @OneToMany(targetEntity = OrderLineItems.class ,cascade = CascadeType.ALL)
    @JoinColumn(name="order_fk", referencedColumnName = "id")
    private List<OrderLineItems> orderLineItemsList;

}
