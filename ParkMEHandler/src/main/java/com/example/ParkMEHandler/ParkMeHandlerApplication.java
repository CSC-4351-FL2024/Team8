package com.example.ParkMEHandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ParkMeHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkMeHandlerApplication.class, args);
	}
}

@RestController
public class HeartbeatController {

    @GetMapping("/heartbeat")
    public String heartbeat() {
        return "OK";
    }
}
