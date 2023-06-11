package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class randomApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/randomRequest")
    public ResponseEntity<String> randomBadRequest() {
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        if (randomBoolean) {
            return ResponseEntity.status(400).body("ok");
        } else {
            return ResponseEntity.status(200).body("not ok");
        }
    }

}
