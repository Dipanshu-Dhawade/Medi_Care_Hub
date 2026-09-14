package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}