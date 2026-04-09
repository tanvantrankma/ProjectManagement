package com.tanvantran.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.tanvantran.dto.PaymentRequest;
import com.tanvantran.dto.PaymentResponse;

@RestController
@RequestMapping("api/v1/payments")
@CrossOrigin
public class PaymentController {


    @Autowired
    private RazorpayClient razorpayClient;


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody PaymentRequest paymentRequest) throws RazorpayException {
        // Create an order
        var orderRequest = new JSONObject();
        orderRequest.put("amount", paymentRequest.getAmount() * 100); // amount in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("payment_capture", 1);
        Order order = razorpayClient.orders.create(orderRequest);

//        JSONObject payment1Request = new JSONObject();
//        payment1Request.put("amount", paymentRequest.getAmount() * 100); // amount in paise
//        payment1Request.put("currency", "INR");
//        String orderId1=order.get("id");
//        payment1Request.put("order_id", orderId1);
//        Payment payment = razorpayClient.payments.createJsonPayment(payment1Request);
//        System.out.println(payment);

        // Save the order ID in the database
        
        // ...

        // Return the order ID to the client
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRazorpayOrderId(order.get("id"));
        paymentResponse.setApplicationFee(paymentRequest.getAmount().toString());
        paymentResponse.setSecretId("rzp_test_8ZxKiN0ZpqMvLd");
        paymentResponse.setSecretKey("qTrl8lFwUKt6GkV6JZbY75Zc");
        return ResponseEntity.ok(paymentResponse);
    }

//    @PostMapping("/capture")
//    public ResponseEntity<?> capturePayment(@RequestBody PaymentRequest paymentRequest) throws RazorpayException {
//        // Capture the payment
//        Payment payment = razorpayClient.payments.capture(paymentRequest.getPaymentId(),
//                paymentRequest.getAmount() * 100, new JSONObject());
//
//        // Update the order status in the database
//        // ...
//
//        // Return the payment status to the client
//        PaymentResponse paymentResponse = new PaymentResponse();
//        paymentResponse.setPaymentId(paymentRequest.getPaymentId());
//        paymentResponse.setStatus(payment.get("status"));
//        return ResponseEntity.ok(paymentResponse);
//    }
}
