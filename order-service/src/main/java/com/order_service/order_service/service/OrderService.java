package com.order_service.order_service.service;

import com.order_service.order_service.DTO.OrderLineItemDTO;
import com.order_service.order_service.DTO.OrderRequest;
import com.order_service.order_service.model.Order;
import com.order_service.order_service.model.OrderLineItems;
import com.order_service.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemDTOList().
                stream().
                map(this::mapToOrderLineItems).
                toList();
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemDTO orderLineItemDTO) {
        return OrderLineItems.builder()
                .id(orderLineItemDTO.getId())
                .skuCode(orderLineItemDTO.getSkuCode())
                .quantity(orderLineItemDTO.getQuantity())
                .price(orderLineItemDTO.getPrice())
                .build();
    }
}
