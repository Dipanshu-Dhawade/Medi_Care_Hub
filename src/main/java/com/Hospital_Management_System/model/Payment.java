package com.Hospital_Management_System.model;

import com.Hospital_Management_System.constant.PaymentModeConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDate paymentDate;

    private Long amount;

    private String paymentMode;

    private String transactionId;

    private String status;

    @Column(nullable = false)
    private boolean deleted =false;

    // Many payments belong to one bill
    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;
}