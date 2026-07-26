package Practice1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testemplyee {
    public static void main(String[] args) {
        System.out.println("main method started");
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Employee employee1 = (Employee) context.getBean("employee1");

        System.out.println(employee1.getEId() + " " + employee1.getEname());

    }
}


