package com.trade.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.trade.models.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Jedis;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@CrossOrigin
public class OrderService {
    private static final int PORT = 8090;
    private static Jedis jedis = null;
    private final static String ORDER_TO_REPORTING_SERVICE_CHANNEL = "C2";
    @PostMapping("/submitOrder")
    public void submitOrders(@RequestBody Order order) throws JsonParseException {
        // TODO: 11/23/20 Send order to the order validation service using SOAP.


        // TODO: 11/23/20 Send order to be created by the reporting service.
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/order/create"; // TODO: 11/23/20 Change to the online address of the reporting service.
        restTemplate.postForObject(endPoint, order, Order.class);

//        // TODO: 11/23/20 Send rest request to the order validator, if soap isn't implemented.
        RestTemplate restTemplate1 = new RestTemplate();
        String endPoint1 = "http://localhost:8280/orders/" + order.getOrderId(); // TODO: 11/23/20 Change to the online address of the order validation service.
        restTemplate1.postForObject(endPoint1, order, Order.class);

        return;
    }

    private static Jedis getConnection() throws URISyntaxException {
        URI redisURI = null;
        if (System.getenv("REDIS_URL") != null) {
            redisURI = new URI(System.getenv("REDIS_URL"));
        } else {
            redisURI = new URI("http://localhost:" + PORT);
        }
        return (new Jedis(redisURI));
    }
}
