package org.example.bai2;


import org.example.bai2.User.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BtvnBuoi1Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.bai2");
        Customer customer = context.getBean("customer", Customer.class);
        customer.run();
    }
}
