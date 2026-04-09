package com.tanvantran.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountResponseDto {

    private short id;
    private String email;
    private String username;
    private String fullname;

    private OrderDto order;
    private ProductDto product;

}
