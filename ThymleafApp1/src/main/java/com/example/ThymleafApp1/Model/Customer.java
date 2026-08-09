package com.example.ThymleafApp1.Model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customer1")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Cname;
    private String Cemail;
    private String Cphone;
}


