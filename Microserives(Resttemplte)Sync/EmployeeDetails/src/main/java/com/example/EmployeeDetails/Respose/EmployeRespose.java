package com.example.EmployeeDetails.Respose;

import lombok.Data;

@Data
public class EmployeRespose {

    int id;
    String name;
    String email;
    String phone;
    AddressResponse addressResponse;

}
