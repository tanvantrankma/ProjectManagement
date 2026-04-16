package com.tanvantran.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanvantran.dto.OrderDto;

@FeignClient(name = "OrderService", path = "/api/v1")
// @RibbonClient(name = "DepartmentService")
public interface OrderFeignClient {

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") int id);

}
