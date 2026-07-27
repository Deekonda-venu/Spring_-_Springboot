package com.venu.App3.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Employee{

    int Eid;
    String Lname;
    String Fname;
    Long Esal;

    public int getEid() {
        return Eid;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public Long getEsal() {
        return Esal;
    }

    public void setEsal(Long esal) {
        Esal = esal;
    }

    public Employee(int eid) {
        Eid = eid;
    }


    public Employee(@JsonProperty("eid") int eid,
                    @JsonProperty("lname") String lname,
                    @JsonProperty("fname") String fname,
                    @JsonProperty("esal") Long esal) {
        Eid = eid;
        Lname = lname;
        Fname = fname;
        Esal = esal;
        System.out.println("Paramatrizes contructir");
    }

    public Employee (){
        System.out.println("No-org contructer");

    }
}

