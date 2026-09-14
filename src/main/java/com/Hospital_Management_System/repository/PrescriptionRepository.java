package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatient_Id(Long patientId);

    List<Prescription> findByDoctor_Id(Long doctorId);
}