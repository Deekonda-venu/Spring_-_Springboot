package com.example.Order_Service.Clinet;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Customer-Service", url = "http://localhost:9293")
public interface CustomerClinet {

}
