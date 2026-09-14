package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.PatientRequestDto;
import com.Hospital_Management_System.dto.responce.PatientResponceDto;
import com.Hospital_Management_System.model.Patient;

import java.util.List;

public interface PatientService {

      // CREATE
      PatientResponceDto savePatient(Long userId, PatientRequestDto patientRequestDto);

      // READ
      PatientResponceDto getPatientById(Long patientId);

      List<PatientResponceDto> getAllPatients();

      PatientResponceDto getPatientByEmail(String email);

      PatientResponceDto getPatientByPhoneNumber(String phoneNumber);

      // UPDATE
      PatientResponceDto updatePatient(Long patientId, PatientRequestDto patientRequestDto);

      PatientResponceDto patchPatient(Long patientId, PatientRequestDto patientRequestDto);

      // DELETE
      void removePatientById(Long patientId);

      // SOFT DELETE
      void softDeletePatient(Long patientId);
}
