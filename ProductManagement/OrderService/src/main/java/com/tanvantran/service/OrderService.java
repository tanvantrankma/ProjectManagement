package com.tanvantran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tanvantran.dto.AccountDto;
import com.tanvantran.dto.OrderResponeDto;
import com.tanvantran.entity.Order;
import com.tanvantran.feignclient.AccountClient;
import com.tanvantran.form.OrderFormForCreating;
import com.tanvantran.rabbitmqClient.service.RabbitMQSender;
import com.tanvantran.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    @Autowired
    private AccountClient accFeignClient;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    private final OrderRepository orderRepository;
    @Override
    public Order placeOrder(Order order) {
        System.out.println(order);
        return orderRepository.save(order);
    }
    @Override
    public Order getOrderById(short id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public OrderResponeDto createOrder(OrderFormForCreating form) {

        // 2. Gọi UserService
        ResponseEntity<AccountDto> accResponse =
                accFeignClient.getAccountByID(form.getAccountId());

        AccountDto accDto = accResponse.getBody();

        System.out.println("Response From AccountService");
        System.out.println(accDto);

        // 4. Map dữ liệu trả về
        OrderResponeDto dto = new OrderResponeDto();

    
        dto.setOrderNumber(form.getOrderNumber());
        

        dto.setAccountDto(accDto);

        return dto;
    
    
    }
    @Override
    public OrderResponeDto createOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }
}