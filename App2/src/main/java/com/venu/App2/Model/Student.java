package com.venu.App2.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class Student{

    @Value("1")
    public int Sid;
    @Value("venu")
    public String Sname;
    computer com;

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

    public computer getCom() {
        return com;
    }

    @Autowired
    @Qualifier("desktop")
    public void setCom(computer com) {
        this.com = com;
    }

    public Student() {
    }

    public Student(int sid, String sname, computer com) {
        Sid = sid;
        Sname = sname;
        this.com = com;
    }

    public void show() {
        com.show();
    }
}
