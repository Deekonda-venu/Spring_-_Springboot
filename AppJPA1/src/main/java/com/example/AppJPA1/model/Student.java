package com.example.AppJPA1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Student")
@Data

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sid;
    String susername;
    String sfname;
    String slname;
    long sphone;
    String semail;


}
