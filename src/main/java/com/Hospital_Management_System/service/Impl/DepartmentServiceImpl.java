package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.DepartmentRequestDto;
import com.Hospital_Management_System.dto.responce.DepartmentResponceDto;
import com.Hospital_Management_System.model.Department;
import com.Hospital_Management_System.repository.DepartmentRepository;
import com.Hospital_Management_System.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    // =====================================================
    // CREATE
    // =====================================================
    @Override
    public DepartmentResponceDto addDepartment(
            DepartmentRequestDto departmentRequestDto) {

        Department department = new Department();

        department.setName(
                departmentRequestDto.getName()
        );

        Department savedDepartment =
                departmentRepository.save(department);

        return convertToDto(savedDepartment);
    }


    // =====================================================
    // READ ALL
    // =====================================================
    @Override
    public List<DepartmentResponceDto> getAllDepartments() {

        List<Department> departments =
                departmentRepository.findByDeletedFalse();

        return departments.stream()
                .map(this::convertToDto)
                .toList();
    }


    // =====================================================
    // READ BY ID
    // =====================================================
    @Override
    public DepartmentResponceDto getDepartmentById(Long id) {

        Department department =
                departmentRepository
                        .findByIdAndDeletedFalse(id);
        if (department == null) {
            throw new RuntimeException(
                    "Department not found: " + id
            );
        }

        return convertToDto(department);
    }


    // =====================================================
    // COMPLETE UPDATE
    // =====================================================
    @Override
    public DepartmentResponceDto updateDepartment(
            Long id,
            DepartmentRequestDto departmentRequestDto) {

        Department department =
                departmentRepository
                        .findByIdAndDeletedFalse(id);
        if (department == null) {
            throw   new RuntimeException(
                    "Department not found: " + id);
        }

        department.setName(
                departmentRequestDto.getName()
        );

        Department updatedDepartment =
                departmentRepository.save(department);

        return convertToDto(updatedDepartment);
    }


    // =====================================================
    // PATCH
    // =====================================================
    @Override
    public DepartmentResponceDto patchDepartment(
            Long id,
            DepartmentRequestDto departmentRequestDto) {

        Department department =
                departmentRepository
                        .findByIdAndDeletedFalse(id);
        if (department == null) {throw new RuntimeException("Department not found: " + id);}


        if (departmentRequestDto.getName() != null &&
                !departmentRequestDto.getName().isBlank()) {

            department.setName(
                    departmentRequestDto.getName()
            );
        }

        Department updatedDepartment =
                departmentRepository.save(department);

        return convertToDto(updatedDepartment);
    }


    // =====================================================
    // SOFT DELETE
    // =====================================================
    @Override
    public void deleteDepartment(Long id) {

        Department department =
                departmentRepository
                        .findByIdAndDeletedFalse(id);
        if(department==null) new RuntimeException("Department not found: " + id);
        department.setDeleted(true);
        departmentRepository.save(department);
    }


    // =====================================================
    // ENTITY -> DTO
    // =====================================================
    private DepartmentResponceDto convertToDto(
            Department department) {

        DepartmentResponceDto dto =
                new DepartmentResponceDto();

        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCreateAt(department.getCreateAt());

        return dto;
    }
}
