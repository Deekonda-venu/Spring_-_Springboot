package com.example.EmployeeDetails.Respose;

import lombok.Data;

@Data
public class AddressResponse {

    int id;
    String city;
    String state;
    String country;
    String pincode;

}
