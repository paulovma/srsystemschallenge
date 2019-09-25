package com.srsystems.challenge;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
