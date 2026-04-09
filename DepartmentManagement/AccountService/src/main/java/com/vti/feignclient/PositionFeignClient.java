package com.vti.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vti.dto.PossitonDto;

@FeignClient(name = "PositionService", path = "/api/v1")
@RibbonClient(name = "PositionService")
public interface PositionFeignClient {

    @GetMapping("/possitions/{id}")
    public ResponseEntity<PossitonDto> getPositionByID(@PathVariable("id") int id);
}


