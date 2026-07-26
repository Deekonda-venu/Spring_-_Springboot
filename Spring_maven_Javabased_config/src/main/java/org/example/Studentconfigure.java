package org.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Studentconfigure {

    @Bean(name = "student")
    Student getstudent(){

        Student s1 = new Student();
        s1.setSid(101);
        s1.setSname("John");
        s1.setSphone(1234567890L);

        return s1;
    }


}
