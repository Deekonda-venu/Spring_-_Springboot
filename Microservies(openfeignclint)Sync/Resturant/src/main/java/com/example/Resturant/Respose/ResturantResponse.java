package com.example.Resturant.Respose;

import com.example.Resturant.Model.ResturantStatus;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResturantResponse {

    Long id;
    String resturantName;
    String description;
    String phone;
    String email;
    String address;
    String city;
    LocalTime openingTime;
    LocalTime closingTime;
//    @Enumerated(EnumType.STRING)
    ResturantStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;


//    MenuItemsDetails menuItems;

}
