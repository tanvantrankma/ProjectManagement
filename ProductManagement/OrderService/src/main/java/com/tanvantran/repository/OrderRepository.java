package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order getOrderById(short id);
    
}


