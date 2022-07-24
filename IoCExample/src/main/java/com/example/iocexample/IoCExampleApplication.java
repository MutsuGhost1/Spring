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

        /*  OUTPUT - the default bean scope is singleton
                   - default behavior for ClassPathXmlApplicationContext will instantiate all beans when it's created
            Constructor - Student(int no, String name) Student { hashCode=1053744929, no=6, name='Ray' }
            Constructor - Student(int no, String name) Student { hashCode=874153561, no=4, name='Richard' }
            Constructor - Student(int no, String name) Student { hashCode=2027227708, no=0, name='dummy' }
            Constructor - Student(int no, String name) Student { hashCode=800497654, no=15, name='Tom' }
            Student { hashCode=2027227708, no=0, name='dummy' }
            Student { hashCode=1778877569, no=15, name='Tom' }
            Student { hashCode=800497654, no=15, name='Tom' }
            Student { hashCode=1053744929, no=6, name='Ray' }
            Student { hashCode=874153561, no=4, name='Richard' }
            ContactBook {studentList=[Student { hashCode=2027227708, no=0, name='dummy' }, Student { hashCode=1778877569, no=15, name='Tom' }, Student { hashCode=800497654, no=15, name='Tom' }, Student { hashCode=1053744929, no=6, name='Ray' }, Student { hashCode=874153561, no=4, name='Richard' }] }
            destroy: Student { hashCode=1778877569, no=15, name='Tom' }
         */
    }
}
