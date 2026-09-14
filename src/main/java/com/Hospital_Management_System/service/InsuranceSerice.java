package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.InsuranceRequestDto;
import com.Hospital_Management_System.dto.responce.InsuranceResponceDto;
import com.Hospital_Management_System.model.Insurance;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface InsuranceSerice {

    public Insurance assingInsuranceToPatient(Insurance  insurance,  Long patient_id);

    public Boolean deassingInsuranceToPatient(Long patientid);

    public InsuranceResponceDto getInsuranceById(Long insurance_id);
    public List<InsuranceResponceDto> getAllInsurance(Long insurance_id);
    public InsuranceResponceDto updateInsurance(Long insurance_id, InsuranceRequestDto insuranceRequestDto);
    }
