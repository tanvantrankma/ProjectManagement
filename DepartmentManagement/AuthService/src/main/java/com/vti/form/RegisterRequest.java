package com.vti.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String username;
    private String fullname;
    private short departmentId;
    private short positionId;
    private String password;
}


