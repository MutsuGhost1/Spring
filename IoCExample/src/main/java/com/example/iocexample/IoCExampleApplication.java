package com.example.iocexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class IoCExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(IoCExampleApplication.class, args);

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("IoCExample.xml");

        System.out.println(context.getBean("student-0", Student.class));
        System.out.println(context.getBean("student-1", Student.class));
        System.out.println(context.getBean("student-1-1", Student.class));
        System.out.println(context.getBean("student-2", Student.class));
        System.out.println(context.getBean("student-3", Student.class));

        System.out.println(context.getBean("contact-book", ContactBook.class));

        context.registerShutdownHook();
    }
}
