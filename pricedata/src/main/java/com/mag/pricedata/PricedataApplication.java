package com.mag.pricedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

@EnableEurekaClient
@SpringBootApplication
public class PricedataApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricedataApplication.class, args);
        new Scanner(System.in)
                .nextLine();
    }

}
