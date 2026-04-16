package com.vti.service;


import com.vti.entity.Account;
import com.vti.form.AuthRequest;
import com.vti.form.RegisterRequest;

public interface IAuthService {
    public String login(AuthRequest request);

    public Account register(RegisterRequest request);
}



