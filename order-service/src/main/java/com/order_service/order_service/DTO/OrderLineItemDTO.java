package com.order_service.order_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemDTO {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
