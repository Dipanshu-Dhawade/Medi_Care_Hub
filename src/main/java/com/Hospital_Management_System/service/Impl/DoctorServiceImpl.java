package com.Hospital_Management_System.service.Impl;


import com.Hospital_Management_System.dto.request.DoctorRequestDto;
import com.Hospital_Management_System.dto.responce.DoctorResponceDto;
import com.Hospital_Management_System.enums.Role;
import com.Hospital_Management_System.model.Department;
import com.Hospital_Management_System.model.Doctor;
import com.Hospital_Management_System.model.User;
import com.Hospital_Management_System.repository.DepartmentRepository;
import com.Hospital_Management_System.repository.DoctorRepository;
import com.Hospital_Management_System.repository.UserRepository;
import com.Hospital_Management_System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepositoryl;

    @Override
    public DoctorResponceDto saveDoctor(Long userId, Long departId, DoctorRequestDto doctorRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Department department = departmentRepository.findById(departId)
                .orElseThrow(() -> new RuntimeException("Department not found: " + departId));

        Role userRole = userRepository.findUserRole(userId);
        if (!userRole.equals(Role.Doctor)){
            throw new RuntimeException("you not have Doctor Role");
        }
        boolean exists = doctorRepository.existsByPhone(doctorRequestDto.getPhone());
        if (exists) {
            throw new RuntimeException("Doctor already exists with phone: "
                    + doctorRequestDto.getPhone()
            );
        }
        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.getDepartment().add(department);
        doctor.setPhone(doctorRequestDto.getPhone());
        doctor.setEmail(doctorRequestDto.getEmail());
        doctor.setName(doctorRequestDto.getName());
        doctor.setSpecialization(doctorRequestDto.getSpecialization());
        doctor.setExprience_years(doctorRequestDto.getExprience_years());
        // Save NEW doctor
        Doctor savedoctor = doctorRepository.save(doctor);

        return new DoctorResponceDto(savedoctor.getId(), savedoctor.getName(), savedoctor.getSpecialization());
    }

    @Override
    public DoctorResponceDto getDoctorById(Long userId) {
        Doctor doctor = doctorRepository.findByUser_Id(userId);
        if (doctor == null) {
            throw new RuntimeException("Doctor is not Found: " + userId);
        }
        return new DoctorResponceDto(doctor.getId(), doctor.getName(), doctor.getSpecialization());
    }

    @Override
    public List<DoctorResponceDto> getAllDoctors() {
        List<Doctor> allDoctor = doctorRepository.findAll();
        if (allDoctor.isEmpty()) throw new RuntimeException("Doctor is Empty");

        List<DoctorResponceDto> doctorResponceDtos = new ArrayList<>();
        for (var doctor : allDoctor) {
            DoctorResponceDto dto = new DoctorResponceDto();
            dto.setName(doctor.getName());
            dto.setId(doctor.getId());
            doctorResponceDtos.add(dto);
        }
        return doctorResponceDtos;
    }

    @Override
    public DoctorResponceDto updateDoctor(Long userId, DoctorRequestDto doctorRequestDto) {
        Doctor doctorobj = doctorRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("Doctor is not Found" + userId));

        doctorobj.setName(doctorRequestDto.getName());
        doctorobj.getDepartment().clear();
        doctorobj.setDepartment(doctorRequestDto.getDepartment());
        doctorobj.setPhone(doctorRequestDto.getPhone());
        doctorobj.setEmail(doctorRequestDto.getEmail());
        doctorobj.setExprience_years(doctorRequestDto.getExprience_years());
        doctorobj.setSpecialization(doctorRequestDto.getSpecialization());

        Doctor save = doctorRepository.save(doctorobj);
        return new DoctorResponceDto(save.getId(), save.getName(), save.getSpecialization());
    }

    @Override
    public DoctorResponceDto patchDoctor(
            Long doctorId,
            DoctorRequestDto doctorRequestDto) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor is not found: " + doctorId
                        ));
        if (doctorRequestDto.getName() != null) {
            doctor.setName(doctorRequestDto.getName());
        }
        if (doctorRequestDto.getGender() != null) {
            doctor.setGender(doctorRequestDto.getGender());
        }

        if (doctorRequestDto.getSpecialization() != null) {
            doctor.setSpecialization(
                    doctorRequestDto.getSpecialization()
            );
        }

        if (doctorRequestDto.getPhone() != null) {
            doctor.setPhone(
                    doctorRequestDto.getPhone()
            );
        }

         if(doctorRequestDto.getExprience_years()<0)
             throw  new RuntimeException("Exprience_years are under 0");

        if (doctorRequestDto.getExprience_years() >0) {
            doctor.setExprience_years(
                    doctorRequestDto.getExprience_years()
            );
        }

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return new DoctorResponceDto(
                updatedDoctor.getId(),
                updatedDoctor.getName(),
                updatedDoctor.getSpecialization()
        );
    }

    @Override
    public void deleteDoctor(Long userId) {
        Doctor doctor = doctorRepository.findById(userId).
                orElseThrow(() -> new RuntimeException("Doctor is not Found" + userId));
        doctorRepository.deleteById(userId);
    }
    @Override
    public List<DoctorResponceDto> getByDepartmentId(Long departmentId) {
        List<Doctor> allDoctor = doctorRepository.findAllDoctor(departmentId);
        if (allDoctor.isEmpty()) throw new RuntimeException("Department is Empty" + departmentId);

        List<DoctorResponceDto> doctorResponceDtos = new ArrayList<>();
        for (var doctor : allDoctor) {
            DoctorResponceDto dto = new DoctorResponceDto();
            dto.setId(doctor.getId());
            dto.setName(doctor.getName());
            dto.setSpecialization(doctor.getSpecialization());
            doctorResponceDtos.add(dto);
        }

        return doctorResponceDtos;
    }

    @Override
    public List<DoctorResponceDto> searchDoctors(String keyword) {
        return List.of();
    }
}
