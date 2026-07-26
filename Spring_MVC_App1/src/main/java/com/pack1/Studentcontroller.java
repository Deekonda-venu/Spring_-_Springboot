package com.pack1;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Studentcontroller {

    @RequestMapping("/login")
    public String login(student s){
        return "Home";
    }

    @RequestMapping("/Home")
    public  String Home(){
        return "Home";
    }
}
