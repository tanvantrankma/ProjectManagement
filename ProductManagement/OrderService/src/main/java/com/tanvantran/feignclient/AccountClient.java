package com.tanvantran.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tanvantran.dto.AccountDto;



@FeignClient(name = "userservice", path = "/api/v1") // tên service bên Eureka / Docker
public interface AccountClient {

    @GetMapping("/accounts/{id}")
    ResponseEntity<AccountDto> getAccountByID(@PathVariable("id") Long id);
}
