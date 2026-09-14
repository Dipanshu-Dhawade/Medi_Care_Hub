package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.enums.AdmissionStatus;
import com.Hospital_Management_System.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    List<Admission> findByStatusEnumsOrderByAdmissionAsc(AdmissionStatus admissionStatus);

    List<Admission> findByStatusEnums(AdmissionStatus admissionStatus);

    List<Admission> findByBedIdAndStatusEnumsOrderByAdmissionAsc(Long id, AdmissionStatus admissionStatus);
}