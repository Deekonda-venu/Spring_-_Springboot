package com.example.Order_Service.Response;

@Data
public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressRespose address;
}
