package com.tanvantran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.GenericResponse;
import com.tanvantran.dto.OrderDto;
import com.tanvantran.entity.Order;
import com.tanvantran.rabbitmqClient.service.RabbitMQSender;
import com.tanvantran.service.IOrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final IOrderService orderService;

    @Autowired
    private RabbitMQSender rabbitMQSender;


    @PostMapping
    public GenericResponse<Order> placeOrder(@RequestBody Order order) {

        rabbitMQSender.sendOrderCreatedEvent(order);

        return GenericResponse.<Order>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(orderService.placeOrder(order))
                .build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOrderByID(@PathVariable(name = "id") short id) {
        Order order = orderService.getOrderById(id);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderNumber(order.getOrderNumber());
        

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}