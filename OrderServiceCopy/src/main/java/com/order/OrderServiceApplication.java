package com.order;

import com.order.bean.OrderItem;
import com.order.bean.UserOrder;
import com.order.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.order")
@EnableJpaRepositories(basePackages = "com.order.persistence")
@EntityScan(basePackages = "com.order.bean")
public class OrderServiceApplication implements CommandLineRunner {
    private OrderServiceInterface orderService;

    @Autowired
    public void setOrderService(OrderServiceInterface orderService) {
        this.orderService = orderService;
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem("P101",1));
        items.add(new OrderItem("P102",2));
        items.add(new OrderItem("P104",3));
        orderService.createOrder(new UserOrder("Ravi", items));
        List<OrderItem> items1 = new ArrayList<>();
        items1.add(new OrderItem("P101",1));
        items1.add(new OrderItem("P104",2));
        orderService.createOrder(new UserOrder("Ganesh", items1));
    }
}
