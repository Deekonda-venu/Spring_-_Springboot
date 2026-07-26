package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Studentconfigure.class);
        Student student = (Student) context.getBean("student");

        student.display();

    }


}
