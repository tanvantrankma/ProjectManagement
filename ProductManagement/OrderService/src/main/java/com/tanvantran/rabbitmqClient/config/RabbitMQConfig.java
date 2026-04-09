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
