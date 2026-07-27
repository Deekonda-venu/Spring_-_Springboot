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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/getempy/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        return new ResponseEntity<>(new Employee(1, "deekonda", "venu", 70000L), HttpStatus.OK);
    }

//    @GetMapping("/getempy/{id}")
//    public Employee getEmployeeById(@PathVariable int id){
//        return new Employee(id);
//    }

    @GetMapping("/getempy/{id}/{lname}/{fname}/{Esal}")
    public Employee getEmployeeById(@PathVariable int id,
                                    @PathVariable String lname ,
                                    @PathVariable String fname,
                                    @PathVariable Long Esal){
        return new Employee(id, lname, fname, Esal);
    }

    @GetMapping("/getempy/info")
    public Employee getEmployeebyreqeatpara(@RequestParam int id,
                                            @RequestParam String lname,
                                            @RequestParam String fname,
                                            @RequestParam Long Esal){

        return new Employee(id, lname, fname, Esal); // in post like this GET http://localhost:9000/api/v1/getempy/info?id=1&lname=deekonda&fname=venu&Esal=70000
    }

    @DeleteMapping("/Delete/{id}")
    public String Deleteemployebyid(@PathVariable int id){
        return "Employee by id is deleted: " + id;
    }

    @PutMapping("/update/{id}") // putmapping means uipdate
    public String Updateemployebyid(@RequestBody int id){
        return "Employee by id is updated: " + id;
    }




}
