package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.enums.AdmissionStatus;
import com.Hospital_Management_System.enums.BedStatus;
import com.Hospital_Management_System.model.*;
import com.Hospital_Management_System.repository.*;
import com.Hospital_Management_System.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdmissionServiceImpl implements AdmissionService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdmissionRepository admissionRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final BedRepository bedRepository;

    @Transactional
    @Override
    public Admission addAdmission(
            Long prescription_id,
            Long patient_id,
            Long doctor_id,
            JSONObject admission
    ) {
        Patient patient = patientRepository.findById(patient_id)
                .orElseThrow(() ->
                        new RuntimeException("Patient is not found"));

        Doctor doctor = doctorRepository.findById(doctor_id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor is not found"));

        prescriptionRepository.findById(prescription_id)
                .orElseThrow(() ->
                        new RuntimeException("Prescription is not found"));

        Long bedId = Long.parseLong(
                admission.get("bedid").toString()
        );

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new RuntimeException("Bed is not found"));


        Admission admission1 = new Admission();

        admission1.setPatient(patient);
        admission1.setDoctor(doctor);
        admission1.setAdmission(LocalDateTime.now());

        admission1.setReson(
                admission.get("reson").toString()
        );
        if (bed.getStatus() == BedStatus.Available) {

            admission1.setBed(bed);
            admission1.setStatusEnums(AdmissionStatus.Active);

            bed.setStatus(BedStatus.Occupied);

            bedRepository.save(bed);
        }


        else if (bed.getStatus() == BedStatus.Occupied) {
            admission1.setBed(bed);
            admission1.setStatusEnums(AdmissionStatus.Waiting);
        }
        else {

            throw new RuntimeException(
                    "Bed is currently " + bed.getStatus()
            );
        }

        return admissionRepository.save(admission1);
    }
}