package Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Teststudent {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("student.xml");
        Student student = (Student) context.getBean("student1");
        student.display(); // this cont
        // directly we can pring with get id and name
        System.out.println("Student ID: " + student.getSid());
        System.out.println("Student Name: " + student.getSname());




    }
}
