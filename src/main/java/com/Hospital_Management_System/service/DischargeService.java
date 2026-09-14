package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.DischargeRequestDto;
import com.Hospital_Management_System.dto.responce.DischargeResponceDto;
import java.util.List;

public interface DischargeService {

    // CREATE - discharge a particular admission
    DischargeResponceDto dischargePerticularPerson(
            Long admissionId,
            DischargeRequestDto dischargeRequestDto
    );

    // READ - get all discharges
    List<DischargeResponceDto> getAllDischarges();

    // READ - get discharge by ID
    DischargeResponceDto getDischargeById(Long id);

    // READ - get discharge by Admission ID
    DischargeResponceDto getDischargeByAdmissionId(Long admissionId);

    // UPDATE - complete update
    DischargeResponceDto updateDischarge(
            Long id,
            DischargeRequestDto dischargeRequestDto
    );

    // PATCH - partial update
    DischargeResponceDto patchDischarge(
            Long id,
            DischargeRequestDto dischargeRequestDto
    );

    // DELETE
    void deleteDischarge(Long id);
}
