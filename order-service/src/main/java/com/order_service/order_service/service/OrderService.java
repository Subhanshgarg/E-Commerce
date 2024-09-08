package com.order_service.order_service.service;
import com.order_service.order_service.DTO.InventoryResponse;
import com.order_service.order_service.DTO.OrderLineItemDTO;
import com.order_service.order_service.DTO.OrderRequest;
import com.order_service.order_service.model.Order;
import com.order_service.order_service.model.OrderLineItems;
import com.order_service.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItems=orderRequest.getOrderLineItemDTOList().
                stream().
                map(this::mapToOrderLineItems).
                toList();
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItems);

        //Call Inventory Service to check availaibilty

        List<String> skuCodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();
        InventoryResponse[] inventoryResponseArray= webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory/", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();
        boolean allItemsInStock= Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if(allItemsInStock) {
            orderRepository.save(order);
        }
        else
            throw new IllegalArgumentException("product is not in stock, please try again");
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
