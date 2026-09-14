package com.Hospital_Management_System.service;


import com.Hospital_Management_System.dto.request.PrescriptionRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionResponseDto;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import tools.jackson.databind.JsonNode;

import java.util.List;

public interface PrescriptionService {

    PrescriptionResponseDto createPrescription(
            Long patient_id , Long doctor_id, JsonNode json);

    PrescriptionResponseDto getPrescriptionById(Long prescriptionId);

    List<JsonNode> getAllPrescriptions();

    List<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId);

    List<PrescriptionResponseDto> getPrescriptionsByDoctor(Long doctorId);

    PrescriptionResponseDto updatePrescription(
            Long prescriptionId,
            String requestDto) throws ParseException;

    PrescriptionResponseDto patchPrescription(
            Long prescriptionId,
            PrescriptionRequestDto requestDto);

    void deletePrescription(Long prescriptionId);

    public void  deletePermenantPrescription(Long prescriptionId)


    }