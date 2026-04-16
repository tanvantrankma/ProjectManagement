package com.tanvantran.rabbitmqClient.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanvantran.rabbitmqClient.service.NotifyHandler;

@Component
public class OrderEventConsumer {
     @Autowired
    private NotifyHandler notifyHandler;

    @RabbitHandler
    @RabbitListener(queues = "order.notification.queue")
    public void consume(String message) {

        System.out.println("Received message from RabbitMQ: " + message);

        notifyHandler.handleOrderNotification(message);
    }
}
