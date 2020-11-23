package com.trade.client.services;

import com.trade.client.models.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@CrossOrigin
public class OrderService {
    @PostMapping("/submitOrder")
    public Flux<Order> submitOrders(ModelMap map, @RequestBody Order order) {
        Flux<Order> response = WebClient.create("http://localhost:8280").post()
                .uri("/orders/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToFlux(Order.class);
        return response;
    }
}
