package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.DischargeRequestDto;
import com.Hospital_Management_System.dto.responce.DischargeResponceDto;
import com.Hospital_Management_System.model.Admission;
import com.Hospital_Management_System.model.Discharge;
import com.Hospital_Management_System.repository.AdmissionRepository;
import com.Hospital_Management_System.repository.DischargeRepository;
import com.Hospital_Management_System.service.DischargeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DischargeServiceImpl implements DischargeService {

    private final DischargeRepository dischargeRepository;
    private final AdmissionRepository admissionRepository;
    private final ModelMapper modelMapper;


    // CREATE
    @Override
    public DischargeResponceDto dischargePerticularPerson(
            Long admissionId,
            DischargeRequestDto dischargeRequestDto) {

        Admission admission = admissionRepository.findById(admissionId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Your admission is not done: " + admissionId
                        ));

        // Check whether this admission is already discharged
        if (dischargeRepository.findByAdmissionId(admissionId).isPresent()) {
            throw new RuntimeException(
                    "This admission is already discharged: " + admissionId
            );
        }

        Discharge discharge = Discharge.builder()
                .dischargeDate(dischargeRequestDto.getDischargeDate())
                .summary(dischargeRequestDto.getSummary())
                .admission(admission)
                .nextFollowUp(dischargeRequestDto.getNextFollowUp())
                .build();

        Discharge savedDischarge = dischargeRepository.save(discharge);

        return modelMapper.map(
                savedDischarge,
                DischargeResponceDto.class
        );
    }


    // READ ALL
    @Override
    public List<DischargeResponceDto> getAllDischarges() {

        List<Discharge> discharges = dischargeRepository.findAll();

        return discharges.stream()
                .map(discharge ->
                        modelMapper.map(
                                discharge,
                                DischargeResponceDto.class
                        )
                )
                .toList();
    }


    // READ BY ID
    @Override
    public DischargeResponceDto getDischargeById(Long id) {

        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Discharge not found: " + id
                        ));

        return modelMapper.map(
                discharge,
                DischargeResponceDto.class
        );
    }


    // READ BY ADMISSION ID
    @Override
    public DischargeResponceDto getDischargeByAdmissionId(
            Long admissionId) {

        Discharge discharge = (Discharge) dischargeRepository
                .findByAdmissionId(admissionId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Discharge not found for admission: "
                                        + admissionId
                        ));

        return modelMapper.map(
                discharge,
                DischargeResponceDto.class
        );
    }


    // COMPLETE UPDATE
    @Override
    public DischargeResponceDto updateDischarge(
            Long id,
            DischargeRequestDto dischargeRequestDto) {

        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Discharge not found: " + id
                        ));

        discharge.setDischargeDate(
                dischargeRequestDto.getDischargeDate()
        );

        discharge.setSummary(
                dischargeRequestDto.getSummary()
        );

        discharge.setNextFollowUp(
                dischargeRequestDto.getNextFollowUp()
        );

        Discharge updatedDischarge =
                dischargeRepository.save(discharge);

        return modelMapper.map(
                updatedDischarge,
                DischargeResponceDto.class
        );
    }


    // PATCH
    @Override
    public DischargeResponceDto patchDischarge(
            Long id,
            DischargeRequestDto dischargeRequestDto) {

        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Discharge not found: " + id
                        ));

        if (dischargeRequestDto.getDischargeDate() != null) {
            discharge.setDischargeDate(
                    dischargeRequestDto.getDischargeDate()
            );
        }

        if (dischargeRequestDto.getSummary() != null) {
            discharge.setSummary(
                    dischargeRequestDto.getSummary()
            );
        }

        if (dischargeRequestDto.getNextFollowUp() != null) {
            discharge.setNextFollowUp(
                    dischargeRequestDto.getNextFollowUp()
            );
        }

        Discharge updatedDischarge =
                dischargeRepository.save(discharge);

        return modelMapper.map(
                updatedDischarge,
                DischargeResponceDto.class
        );
    }


    // DELETE
    @Override
    public void deleteDischarge(Long id) {

        Discharge discharge = dischargeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Discharge not found: " + id
                        ));

        dischargeRepository.delete(discharge);
    }
}
