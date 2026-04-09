package com.tanvantran.rabbitmqClient.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tanvantran.rabbitmqClient.constants.Constants;

@Configuration
public class RabbitMQConfig {

    // public static final String EXCHANGE = "main.exchange";
    // public static final String ROUTING_KEY_ACCOUNT = "account.created";
    // public static final String QUEUE_ACCOUNT_CREATED = "account.created.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE);
    }

    @Bean
    public Queue accountCreatedQueue() {
        return new Queue(Constants.QUEUE_ACCOUNT_CREATED);
    }

    @Bean
    public Binding bindingAccountCreated(Queue accountCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(accountCreatedQueue).to(exchange).with(Constants.ROUTING_KEY_ACCOUNT);
    }
}
