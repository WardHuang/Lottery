package io.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "io.lottery")
@EnableFeignClients
public class LotteryApp {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(LotteryApp.class);

    }
}