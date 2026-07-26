package com.venu.App2;

import com.venu.App2.Model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App2Application {

	public static void main(String[] args) {


		// insted Configataionapplication context use Appliaton context
		ApplicationContext context = SpringApplication.run(App2Application.class, args);

		Student s1 = context.getBean("student", Student.class);
		System.out.println(s1.Sid);
		System.out.println(s1.Sname);
//		System.out.println();
		s1.show();

	}

}
