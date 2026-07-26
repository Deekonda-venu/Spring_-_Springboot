package com.Exple.learn.Controller;

import com.Exple.learn.Model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
public class Employeecontroller {

    @GetMapping("/")
    String hello(){
        return "hello Spring boot this is venu";
    }


    @GetMapping("/emp")
    Employee emp(){
        return new Employee(1,"venu1",70000);
    }


    @GetMapping("/Allemployeelist")
    List<Employee> allemp(){
        List<Employee> list = new ArrayList<>();
        Employee e1 = new Employee(1,"venu", 70000);
        Employee e2 = new Employee(2,"venu", 70000);
        Employee e3 = new Employee(3,"venu", 70000);
        Employee e4 = new Employee(4,"venu", 70000);
        Employee e5 = new Employee(5,"venu", 70000);

        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);


        return list;
    }




}
