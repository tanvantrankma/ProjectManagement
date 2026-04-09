package com.tanvantran.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanvantran.dto.ProductDto;

@FeignClient(name = "ProductService", path = "/api/v1")
// @RibbonClient(name = "PositionService")
public interface ProductFeignClient {

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable("id") int id);
}

