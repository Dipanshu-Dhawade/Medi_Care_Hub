package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}