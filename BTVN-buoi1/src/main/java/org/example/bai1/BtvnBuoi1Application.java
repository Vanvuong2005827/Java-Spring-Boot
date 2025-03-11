package org.example.bai1;

import org.example.bai1.User.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BtvnBuoi1Application {

    public static void main(String[] args) {
//        SpringApplication.run(BtvnBuoi1Application.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.bai1");
        User user = (User) context.getBean("user");
        user.makeCall();
    }

}
