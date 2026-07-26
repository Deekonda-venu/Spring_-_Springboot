package com.Exple.learn.Model;

public class Employee {

    int Eid;
    String Ename;
    int Esalary;

    public int getEid() {
        return Eid;
    }

    public String getEname() {
        return Ename;
    }

    public int getEsalary() {
        return Esalary;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public void setEsalary(int esalary) {
        Esalary = esalary;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Employee{" +
                "Eid=" + Eid +
                ", Ename='" + Ename + '\'' +
                ", Esalary=" + Esalary +
                '}';
    }

    public Employee(int esalary, String ename, int eid) {
        Esalary = esalary;
        Ename = ename;
        Eid = eid;
    }
}
