package com.example.Order_Service.Controller;

import com.example.Order_Service.Model.OrderDetails;
import com.example.Order_Service.Response.OrderResponse;
import com.example.Order_Service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/Order/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/CreateOrder")
    public OrderResponse createOrder(@RequestBody OrderDetails orderDetails) {
        return orderService.createOrder(orderDetails);
    }
}
