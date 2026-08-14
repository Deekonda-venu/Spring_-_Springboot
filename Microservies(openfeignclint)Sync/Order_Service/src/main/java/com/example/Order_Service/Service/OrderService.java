package com.example.Order_Service.Service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDetailesRepo orderDetailesRepo;
    @Autowired
    private OrderitemsRepo orderitemsRepo;
    @Autowired
    private ResturnatClinet resturnatClinet;
    @Autowired
    private CustomerClinet customerClinet;
    @Autowired
    private Menuitemsclinet menuitemsclinet;



}
