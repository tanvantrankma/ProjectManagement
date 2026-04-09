package com.tanvantran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.form.AuthRequest;
import com.tanvantran.form.RegisterRequest;
import com.tanvantran.service.IAuthService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    // Login tài khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        return ResponseEntity.ok(authService.login(request));

    }

    // // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}


