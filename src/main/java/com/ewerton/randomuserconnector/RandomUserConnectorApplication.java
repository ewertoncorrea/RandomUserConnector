package com.ewerton.randomuserconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RandomUserConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomUserConnectorApplication.class, args);
    }

}
