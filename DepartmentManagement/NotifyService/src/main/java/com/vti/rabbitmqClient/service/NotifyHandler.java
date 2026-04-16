package com.vti.rabbitmqClient.service;

import org.springframework.stereotype.Service;

@Service
public class NotifyHandler {

    public void handleAccountNotification(String message) {
        // Ở đây bạn có thể mở rộng: gửi email, push notification, ghi log, lưu DB,...
        System.out.println("You Create New Account Success, Thank you: " + message);
    }
}



