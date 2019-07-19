package com.internship.gpforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GpforumApplication {
  
    public static void main(String[] args) {
        SpringApplication.run(GpforumApplication.class, args);
    }

}
