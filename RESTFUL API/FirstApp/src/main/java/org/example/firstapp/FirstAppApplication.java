package org.example.firstapp;

import org.example.firstapp.run.Location;
import org.example.firstapp.run.Run;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class FirstAppApplication {

    private static final Logger log = LoggerFactory.getLogger(FirstAppApplication.class);

    public static void main(String[] args){
        SpringApplication.run(FirstAppApplication.class, args);
        log.info("Hello World");
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Run run = new Run(1, "First run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3, Location.OUTDOOR);
            log.info("Run: " + run);
        };
    }

}
