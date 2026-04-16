package com.tanvantran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponeDto {

    
    private Long Id;

    private String orderNumber;

    private AccountDto accountDto;

}
