package com.venu.App3.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.venu.App3.Model.Employee;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1")
public class Employeecontroller{

    @GetMapping
    public String hello(){
        return "Hello SB from APP3";
    }

    @GetMapping("/getempy")
    Employee getempy(){

        return new Employee(1, "deekonda", "venu", 70000L);
    }

    @GetMapping("/getlist")
    List<Employee> getListemploy(){
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1, "deekonda", "venu", 70000L));
        list.add(new Employee(2, "deekonda", "venu", 70000L));
        list.add(new Employee(3, "deekonda", "venu", 70000L));
        list.add(new Employee(4, "deekonda", "venu", 70000L));
        list.add(new Employee(5, "deekonda", "venu", 70000L));

        return list;
//        List<>
    }

    @PostMapping("/createemplyee")
    @ResponseStatus(HttpStatus.CREATED) // gigves the status code 201 , creatijg anyhtng
    String postemplyemaping(@RequestBody Employee employee){

//        return employee; this will give what ever you ctreated boyd

        return "suscceslly creates 201";
    }

}
