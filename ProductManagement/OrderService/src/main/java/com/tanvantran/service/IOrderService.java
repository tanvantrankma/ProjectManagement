package com.tanvantran.service;

import com.tanvantran.dto.OrderResponeDto;
import com.tanvantran.entity.Order;
import com.tanvantran.form.OrderFormForCreating;

public interface IOrderService {
    Order placeOrder(Order order);

    Order getOrderById(short id);

    OrderResponeDto createOrder(Order order);

    OrderResponeDto createOrder(OrderFormForCreating form);
}
