package com.example.Order_Service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@JsonPropertyOrder({"id", "customerId", "restaurantId", "deliveryAddressId", "status", "subtotal", "tax", "deliveryFee", "totalAmount", "paymentStatus", "createdAt", "updatedAt"})
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private Long deliveryAddressId;
    private List<OrderItemRequest> items;
}
