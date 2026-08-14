package com.example.Order_Service.Clinet;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "MenuService", url = "http://localhost:9292")
public interface Menuitemsclinet {

}
