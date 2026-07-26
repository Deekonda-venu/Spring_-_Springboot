package org.example;

import org.springframework.beans.factory.annotation.Value;

public class Student {

    @Value(value = "123") // defult value , we have to remove from config file and ther one more is there place holder will ${}#
    int Sid;


    @Value(value = "venu")
    String Sname;

    @Value(value = "8239878769")
    Long Sphone;

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public Long getSphone() {
        return Sphone;
    }

    public void setSphone(Long sphone) {
        Sphone = sphone;
    }

    public Student() {

        System.out.println("No-org contructer");

    }

    public Student(int sid, String sname, Long sphone) {
        System.out.println("Parametrized org contructer");
        Sid = sid;
        Sname = sname;
        Sphone = sphone;
    }

    public void display(){
        System.out.println("Student ID: " + Sid);
        System.out.println("Student Name: " + Sname);
        System.out.println("Student Phone: " + Sphone);
    }
}
