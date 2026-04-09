package com.tanvantran.rabbitmqClient.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanvantran.entity.Account;
import com.tanvantran.rabbitmqClient.constants.Constants;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAccountCreatedEvent(Account account) {
        // Tạo dữ liệu trước khi gửi
        String message = account.getId() + ", " + account.getUsername() + ", " + account.getFullname() + ", " +
                account.getEmail() + ", " + account.getOrderID() + ", " + account.getProductID()
                + ", " +
                ", " + account.getCreateDate();
        // Gửi dữ liệu tới Queue RabbitMQ
        rabbitTemplate.convertAndSend(
                Constants.EXCHANGE,
                Constants.ROUTING_KEY_ACCOUNT,
                message);
        // Log thông báo gửi thành công
        System.out.println(" Sent message successfully to RabbitMQ Server: " + message);
    }

}

