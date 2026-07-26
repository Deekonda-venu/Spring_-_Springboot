package Student;

public class Student {

    int Sid;
    String Sname;

    public int getSid() {
        return Sid;
    }

    public void setSid(int Sid) {
        this.Sid = Sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }


    public void display() {
        System.out.println("Student ID: " + Sid);
        System.out.println("Student Name: " + Sname);
    }
    public Student() {
        System.out.println("no - org contruct");
    }

    public Student(int Sid, String Sname) {
        System.out.println("parametrized org contruct");
        this.Sid = Sid;
        this.Sname = Sname;
    }


}
