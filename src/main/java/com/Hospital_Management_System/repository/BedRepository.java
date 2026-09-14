package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.enums.BedStatus;
import com.Hospital_Management_System.model.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedRepository extends JpaRepository<Bed, Long> {
    List<Bed> findByStatus(BedStatus bedStatus);
}