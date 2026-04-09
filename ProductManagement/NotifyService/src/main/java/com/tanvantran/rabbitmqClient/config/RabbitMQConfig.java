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

    // public static final String ACCOUNT_QUEUE = "account.queue";
    // public static final String EXCHANGE = "main.exchange";
    // public static final String ROUTING_KEY_ACCOUNT = "account.created";

    @Bean
    public Queue accountQueue() {
        return new Queue(Constants.QUEUE_ACCOUNT_CREATED);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE);
    }

    @Bean
    public Binding bindingAccountQueue() {
        return BindingBuilder.bind(accountQueue())
                .to(exchange())
                .with(Constants.ROUTING_KEY_ACCOUNT);
    }


    @Bean
    public Queue orderQueue() {
        return new Queue(Constants.ORDER_QUEUE);
    }

    @Bean
    public TopicExchange exchangeOrder() {
        return new TopicExchange(Constants.ORDER_EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(orderQueue())
                .to(exchangeOrder())
                .with(Constants.ORDER_ROUTING_KEY);
    }

}



