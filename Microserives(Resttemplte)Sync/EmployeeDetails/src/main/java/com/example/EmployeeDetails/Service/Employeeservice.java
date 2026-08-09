package com.example.EmployeeDetails.Service;

import com.example.EmployeeDetails.Model.Employe;
import com.example.EmployeeDetails.Reposoitary.Employeerepo;
import com.example.EmployeeDetails.Respose.EmployeRespose;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Employeeservice {

    @Autowired
    Employeerepo employeerepo;

    @Autowired
    ModelMapper modelMapper;


    public EmployeRespose getEmploye(int id) {

        Employe employe = employeerepo.findById(id).get();

        EmployeRespose employeRespose = modelMapper.map(employe, EmployeRespose.class);

        return employeRespose;

    }
}

