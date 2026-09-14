package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}