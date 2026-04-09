package com.tanvantran.rabbitmqClient.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanvantran.entity.Order;
import com.tanvantran.feignclient.AccountClient;
import com.tanvantran.rabbitmqClient.constants.Constants;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private AccountClient accFeignClient;

    public void sendOrderCreatedEvent(Order order) {

        // 2. Gọi AccountService để lấy email
        String email = accFeignClient.getAccountByID(order.getAccountId()).getBody().getEmail();

        // 3. Tạo message
        String message = order.getId() + ", "
                + order.getAccountId() + ", "
                + email + ", "
                + order.getOrderStatus();

        // Gửi message tới RabbitMQ
        rabbitTemplate.convertAndSend(
                Constants.ORDER_EXCHANGE,
                Constants.ORDER_ROUTING_KEY,
                message
        );

        // Log
        System.out.println("Sent message successfully to RabbitMQ Server: " + message);
    }
}