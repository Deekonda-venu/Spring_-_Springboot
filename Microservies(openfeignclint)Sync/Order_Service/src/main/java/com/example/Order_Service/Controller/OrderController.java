package com.example.Order_Service.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/Order/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;



}
