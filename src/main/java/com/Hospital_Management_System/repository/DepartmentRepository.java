package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDeletedFalse();

    Department findByIdAndDeletedFalse(Long id);
}