package com.example.ParkMEHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkMeHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkMeHandlerApplication.class, args);
    }
}
