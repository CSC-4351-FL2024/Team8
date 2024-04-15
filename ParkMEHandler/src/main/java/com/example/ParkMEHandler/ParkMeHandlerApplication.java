package com.example.ParkMEHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.boot.CommandLineRunner;

// import com.example.ParkMEHandler.service.ResetService;

@SpringBootApplication
@EnableScheduling
public class ParkMeHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkMeHandlerApplication.class, args);
    }

    // @Autowired
    // private ResetService resetService;

    // @Bean
    // public CommandLineRunner commandLineRunner() {
    // return args -> {
    // resetService.resetUserBookingsAndDecks();
    // };
    // }
}