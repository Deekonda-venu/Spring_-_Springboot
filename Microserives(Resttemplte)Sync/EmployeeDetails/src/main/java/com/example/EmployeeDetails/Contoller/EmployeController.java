package com.example.EmployeeDetails.Contoller;

import com.example.EmployeeDetails.Respose.EmployeRespose;
import com.example.EmployeeDetails.Service.Employeeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/v1")
public class EmployeController {

    @Autowired
    Employeeservice employeeservice;

    @GetMapping("getemployess/{id}")
    public ResponseEntity<EmployeRespose> getEmploye(@PathVariable int id) {
        return ResponseEntity.ok(employeeservice.getEmploye(id));
    }
}
