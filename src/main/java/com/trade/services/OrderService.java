package com.trade.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.trade.models.Order;
import com.trade.models.OrderSubmissionResponse;
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
    private final static String ORDER_TO_REPORTING_SERVICE_CHANNEL = "C2";
    private final static String LINK_TO_REPORTING_SERVICE = "https://trade-reporting-service.herokuapp.com/";
    private final static String LINK_TO_ORDER_VALIDATION_SERVICE = "https://trade-order-validator.herokuapp.com/";
    private static Jedis jedis = null;

    private static Jedis getConnection() throws URISyntaxException {
        URI redisURI = null;
        if (System.getenv("REDIS_URL") != null) {
            redisURI = new URI(System.getenv("REDIS_URL"));
        } else {
            redisURI = new URI("http://localhost:" + PORT);
        }
        return (new Jedis(redisURI));
    }

    @PostMapping("/submitOrder")
    @CrossOrigin
    public OrderSubmissionResponse submitOrders(@RequestBody Order order) {
        System.out.println(order.toString());
        // TODO: 11/23/20 Send order to be created by the reporting service.
        RestTemplate restTemplate = new RestTemplate();


        // TODO: 11/23/20 Change to the online address of the reporting service. => DONE
        String endPoint = "http://localhost:8080/order/create";
        String endPoint1 = "http://localhost:8280/orders/";

        if (System.getenv("online") != null) {
            endPoint = LINK_TO_REPORTING_SERVICE + "/order/create";
            endPoint1 = LINK_TO_ORDER_VALIDATION_SERVICE + "/orders/" + order.getOrderId();
        }else{
            endPoint1 = endPoint1 + order.getOrderId();
        }
        System.out.println("End point #1: " + endPoint);
        System.out.println("End point #2: " + endPoint1);
        restTemplate.postForObject(endPoint, order, Order.class);

//        // TODO: 11/23/20 Send rest request to the order validator, if soap isn't implemented.
        RestTemplate restTemplate1 = new RestTemplate();

        // TODO: 11/23/20 Change to the online address of the order validation service. => DONE
        restTemplate1.postForObject(endPoint1, order, Order.class);

        return (new OrderSubmissionResponse(order.getOrderId(), "created"));
    }
}

