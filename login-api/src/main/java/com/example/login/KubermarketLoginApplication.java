package com.example.login;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.example.common")
@RequiredArgsConstructor
@EnableCaching
public class KubermarketLoginApplication  {

    public static void main(String[] args) {
        SpringApplication.run(KubermarketLoginApplication.class, args);
    }

}
