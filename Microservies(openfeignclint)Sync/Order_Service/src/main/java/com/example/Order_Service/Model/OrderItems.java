package com.example.Order_Service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

//@Entity
//@Table(name = "order_items")
@Data
//@JsonPropertyOrder({"id", "orderId", "menuItemId", "itemName", "quantity", "unitPrice", "totalPrice"})
public class OrderItems {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private Long menuItemId;
    private Integer quantity;

}
