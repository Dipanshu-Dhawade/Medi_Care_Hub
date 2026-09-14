package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Discharge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DischargeRepository extends JpaRepository<Discharge, Long> {
    Optional<Object> findByAdmissionId(Long admissionId);
}