package com.pack1;

public class student {

    int studentid;

    String studentname;

    public student(String studentname, int studentid) {
        this.studentname = studentname;
        this.studentid = studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    @Override
    public String toString() {
        return "student{" +
                "studentid=" + studentid +
                ", studentname='" + studentname + '\'' +
                '}';
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentname() {
        return studentname;
    }

    public int getStudentid() {
        return studentid;
    }
}
