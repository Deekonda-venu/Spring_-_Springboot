package com.example.Addressdetailes.Controller;

import com.example.Addressdetailes.Respose.AddressRespose;
import com.example.Addressdetailes.Service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address/v1")
public class Addresscontroller {

    @Autowired
    private Employeeservice employeeservice;

    @GetMapping("/getaddressbyemplyeeid/{employeeid}")
    public ResponseEntity<AddressRespose> getaddressbyemplyeeid(@PathVariable Long employeeid) {
        return ResponseEntity.ok(employeeservice.getaddressbyemplyeeid(employeeid));
    }
}
