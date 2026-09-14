package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.InsuranceRequestDto;
import com.Hospital_Management_System.dto.responce.InsuranceResponceDto;
import com.Hospital_Management_System.model.Insurance;
import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.repository.InsuranceRepository;
import com.Hospital_Management_System.repository.PatientRepository;
import com.Hospital_Management_System.service.InsuranceSerice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceSericeImpl implements InsuranceSerice {


    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    //add
    @Transactional
    @Override
    public Insurance assingInsuranceToPatient(Insurance insurance, Long patient_id) {
        Patient patientid = patientRepository.findById(patient_id).
                orElseThrow(() -> new RuntimeException("Patient not found Exception"));
        patientid.setInsurance(insurance);
        return insuranceRepository.save(insurance);
    }


    // deleted
    @Transactional
    @Override
    public Boolean deassingInsuranceToPatient(Long patientid) {
        boolean status = false;
        Patient patient = patientRepository.findById(patientid).
                orElseThrow(() -> new RuntimeException("Patient Not  found Exception"));
        if (patient == null) return status;
        patient.setInsurance(null);
        status = true;
        return status;
    }

    //find by id
    @Override
    public InsuranceResponceDto getInsuranceById(Long insurance_id) {
        Insurance insurance = insuranceRepository.findById(insurance_id).
                orElseThrow(() -> new RuntimeException("Insurance Not Found " + insurance_id));

        return new InsuranceResponceDto(insurance.getId(),
                insurance.getPolicyNumber(),
                insurance.getProvider());
    }

    @Override
    public List<InsuranceResponceDto> getAllInsurance(Long insurance_id) {
        List<Insurance> insurances = insuranceRepository.findAll();
        List<InsuranceResponceDto> dtos = new ArrayList<>();

        for (var insurance : insurances) {
            InsuranceResponceDto dto = new InsuranceResponceDto();
            dto.setId(insurance.getId());
            dto.setProvider(insurance.getPolicyNumber());
            dto.setProvider(insurance.getProvider());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public InsuranceResponceDto updateInsurance(Long insurance_id, InsuranceRequestDto insuranceRequestDto) {
        Insurance insurance = insuranceRepository.
                findById(insurance_id).
                orElseThrow(() -> new RuntimeException("Insurance is not Found " + insurance_id));
        Patient patient = patientRepository.findById(insuranceRequestDto.getPatientid()).get();
        insurance.setProvider(insuranceRequestDto.getProvider());
        insurance.setPatient(patient);
        insurance.setCreatedAt(insuranceRequestDto.getCreatedAt());
        insurance.setPolicyNumber(insuranceRequestDto.getPolicyNumber());

        Insurance saveinsurance = insuranceRepository.save(insurance);

        return new   InsuranceResponceDto(saveinsurance.getId()
                ,saveinsurance.getPolicyNumber()
                ,saveinsurance.getProvider());
    }


}
