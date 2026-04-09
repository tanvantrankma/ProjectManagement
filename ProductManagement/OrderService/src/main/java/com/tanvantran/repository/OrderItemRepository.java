package com.tanvantran.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanvantran.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    
}
