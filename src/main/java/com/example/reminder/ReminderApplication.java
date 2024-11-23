package com.example.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ReminderApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ReminderApplication.class, args);
    }

}
