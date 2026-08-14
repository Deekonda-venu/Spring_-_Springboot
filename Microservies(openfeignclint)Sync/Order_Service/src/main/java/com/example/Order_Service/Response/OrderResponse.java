package com.example.Order_Service.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"id", "customerId", "restaurantId", "deliveryAddressId", "status", "subtotal", "tax", "deliveryFee", "totalAmount", "paymentStatus", "createdAt", "updatedAt"})
public class OrderResponse {

    private Long id;
    private Long customerId;
    private Long restaurantId;
    private Long deliveryAddressId;
    private String status;
    private List<MenuItemResponse> items;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal deliveryFee;
    private BigDecimal totalAmount;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
