package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}