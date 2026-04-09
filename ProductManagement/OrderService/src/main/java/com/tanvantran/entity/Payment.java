package com.tanvantran.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Payment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String paymentId;

//     @Column(nullable = false)
//     private Double amount;

//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private PaymentStatus paymentStatus;

//     @Column(nullable = false)
//     private LocalDateTime paymentDateTime;

//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false)
//     private PaymentType paymentType;

// }

@Entity
@Table(name = "Payment", catalog = "order_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "PaymentId")
    private String paymentId;

    @Column(name = "Amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentStatus", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "PaymentPateTime", nullable = false)
    private LocalDateTime paymentDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "PaymentType", nullable = false)
    private PaymentType paymentType;
}
