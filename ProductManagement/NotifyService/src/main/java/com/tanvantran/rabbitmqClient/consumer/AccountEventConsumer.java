package com.tanvantran.rabbitmqClient.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanvantran.rabbitmqClient.constants.Constants;
import com.tanvantran.rabbitmqClient.service.NotifyHandler;

@Component
public class AccountEventConsumer {

    @Autowired
    private NotifyHandler notifyHandler;

    @RabbitHandler
    @RabbitListener(queues = Constants.QUEUE_ACCOUNT_CREATED)
    public void receiveMessage(String message) {
        System.out.println(" Received message From UserService: " + message);
        // Xử lý message nhận được từ Queue RabbitMQ
        notifyHandler.handleAccountNotification(message);
    }
}


