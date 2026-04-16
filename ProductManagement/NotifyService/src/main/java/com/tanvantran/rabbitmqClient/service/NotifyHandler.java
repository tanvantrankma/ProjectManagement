package com.tanvantran.rabbitmqClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotifyHandler {

    public void handleAccountNotification(String message) {
        // Ở đây bạn có thể mở rộng: gửi email, push notification, ghi log, lưu DB,...
        System.out.println("You Create New Account Success, Thank you: " + message);
    }

     @Autowired
    private JavaMailSender mailSender;

    public void handleOrderNotification(String message) {

        try {
            // message format: orderId, accountId, email, status
            String[] parts = message.split(",");

            String orderId = parts[0].trim();
            String accountId = parts[1].trim();
            String email = parts[2].trim();
            String status = parts[3].trim();

            // Tạo email
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setSubject("Order Created Successfully");

            mail.setText(
                    "Hello,\n\n" +
                    "Your order has been created successfully!\n\n" +
                    // "Order ID: " + orderId + "\n" +
                    "Account ID: " + accountId + "\n" +
                    "Status: " + status + "\n\n" +
                    "Thank you for your purchase!"
            );

            // Gửi mail
            mailSender.send(mail);

            System.out.println("Email sent to: " + email);

        } catch (Exception e) {
            System.out.println("Error processing message: " + message);
            e.printStackTrace();
        }
    }
}



