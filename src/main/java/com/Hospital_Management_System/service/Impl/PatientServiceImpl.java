package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.PatientRequestDto;
import com.Hospital_Management_System.dto.responce.PatientResponceDto;
import com.Hospital_Management_System.enums.BloodGroup;
import com.Hospital_Management_System.exception.PhoneNotFoundException;
import com.Hospital_Management_System.model.Address;
import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.model.User;
import com.Hospital_Management_System.repository.PatientRepository;
import com.Hospital_Management_System.repository.UserRepository;
import com.Hospital_Management_System.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;


    @Override
    public PatientResponceDto savePatient(
            Long userId,
            PatientRequestDto patientRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (patientRepository.existsByPhoneNumber(
                patientRequestDto.getPhoneNumber())) {

            throw new RuntimeException("Phone number already exists");
        }

        Patient patient = new Patient();

        patient.setName(patientRequestDto.getName());
        patient.setGender(patientRequestDto.getGender());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setBirthDate(patientRequestDto.getBirthDate());
        patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
        patient.setBloodGroup(patientRequestDto.getBloodGroup());
        patient.setAddress(patientRequestDto.getAddress());

        // Connect existing User
        patient.setUser(user);

        Patient savedPatient = patientRepository.save(patient);

        return new PatientResponceDto(
                savedPatient.getName(),
                savedPatient.getGender(),
                savedPatient.getBloodGroup()
        );
    }
    @Override
    public PatientResponceDto getPatientById(Long userId) {
        Patient patient = patientRepository.findByUser(userId);

                if(patient==null) throw  new RuntimeException("Pateint is not Found");

        PatientResponceDto dto = new PatientResponceDto();
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());
        return dto;

    }

    @Override
    public List<PatientResponceDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) throw new RuntimeException("Pateint are Empty");
        List<PatientResponceDto> patientResponceDtos = new ArrayList<>();
        for (var patient : patients) {
            PatientResponceDto patientResponceDto = new PatientResponceDto();
            patientResponceDto.setGender(patient.getGender());
            patientResponceDto.setBloodGroup(patient.getBloodGroup());
            patientResponceDto.setName(patient.getName());
            patientResponceDtos.add(patientResponceDto);
        }
        return patientResponceDtos;
    }

    @Override
    public PatientResponceDto getPatientByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email);

        PatientResponceDto dto = new PatientResponceDto();
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());

        return dto;
    }

    @Override
    public PatientResponceDto getPatientByPhoneNumber(String phoneNumber) {
        Patient patient = patientRepository.findByPhoneNumber(phoneNumber);
        if(patient==null) throw  new RuntimeException("Your Details is not Present");
        return new PatientResponceDto(patient.getName(),patient.getGender(),patient.getBloodGroup());
    }

    @Override
    public PatientResponceDto updatePatient(Long userId, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.
                findByUser(userId);
        if(patient==null) throw   new RuntimeException("Patient is not Found with "+userId);

        patient.setName(patientRequestDto.getName());
        patient.setGender(patientRequestDto.getGender());
        patient.setBloodGroup(patientRequestDto.getBloodGroup());
        patient.setBirthDate(patientRequestDto.getBirthDate());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setPhoneNumber(patientRequestDto.getPhoneNumber());

        patientRepository.save(patient);

        PatientResponceDto dto = new PatientResponceDto();
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setBloodGroup(patient.getBloodGroup());

       return dto;
    }

    @Override
    public PatientResponceDto patchPatient(Long userId, PatientRequestDto patientRequestDto) {

        Patient patient = patientRepository.findByUser(userId);

                if(patient==null) throw  new RuntimeException("Patient is Not Found by id"+userId);
        if (patientRequestDto.getName() != null) {
            patient.setName(patientRequestDto.getName());
        }
        if (patientRequestDto.getGender() != null) {
            patient.setGender(patientRequestDto.getGender());
        }
        if (patientRequestDto.getBloodGroup() != null) {
            patient.setBloodGroup(patientRequestDto.getBloodGroup());
        }
        if (patientRequestDto.getBirthDate() != null) {
            patient.setBirthDate(patientRequestDto.getBirthDate());
        }
        if (patientRequestDto.getPhoneNumber() != null) {
            patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
        }
        patientRepository.save(patient);

        return new PatientResponceDto(
                patient.getName(),
                patient.getGender(),
                patient.getBloodGroup()
        );
    }

    @Transactional
    @Override
    public void removePatientById(Long userId) {
        Patient patient = patientRepository.findByUser(userId);
          if(patient==null)  throw  new RuntimeException("Patient is not Found " + userId);
         patientRepository.deleteByUser_id(userId);
    }

    @Override
    public void softDeletePatient(Long userId) {
        Patient patient = patientRepository.findByUser(userId);
        if (patient==null) throw  new RuntimeException("Patient is not Found"+userId);
        patient.setDeleted(true);
        patientRepository.save(patient);
    }


}
