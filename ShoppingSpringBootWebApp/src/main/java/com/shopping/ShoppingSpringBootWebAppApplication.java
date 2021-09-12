package com.shopping;

import com.shopping.bean.Cart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.shopping")
public class ShoppingSpringBootWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingSpringBootWebAppApplication.class, args);
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Scope("session")
    Cart getCart() {
        return new Cart();
    }
}
