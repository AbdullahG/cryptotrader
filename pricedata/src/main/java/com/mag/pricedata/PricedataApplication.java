package com.mag.pricedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.util.Scanner;

@EnableEurekaClient
@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class})
@EnableCaching
@EnableScheduling
@EnableWebFlux
@EnableAsync
public class PricedataApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricedataApplication.class, args);
        new Scanner(System.in)
                .nextLine();
    }

}
