package com.example.Order_Service.Clinet;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Resturant", url = "http://localhost:9191")
public interface ResturnatClinet {

    @GetMapping("/GetResturnentdetailsById/{id}")
    ResponseEntity<ResturantResponse> getResturentDetailsById(@PathVariable Long id);


}
