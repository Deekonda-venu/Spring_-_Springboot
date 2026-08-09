package com.example.Addressdetailes.Respose;

import lombok.Data;

@Data
public class AddressRespose {

    Long id;
    Long employeeId;
    String street;
    String city;
    String state;
    String zipCode;

}
