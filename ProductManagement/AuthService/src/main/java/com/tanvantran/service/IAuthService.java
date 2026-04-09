package com.tanvantran.service;

import com.tanvantran.entity.Account;
import com.tanvantran.form.AuthRequest;
import com.tanvantran.form.RegisterRequest;

public interface IAuthService {
    public String login(AuthRequest request);

    public Account register(RegisterRequest request);
}



