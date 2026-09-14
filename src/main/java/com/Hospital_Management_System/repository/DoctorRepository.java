package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Boolean existsByPhone(String phone);


    @Query("""
            select d 
            from Doctor d where  exists(
            select  dept 
            from d.department dept
            where dept.id= :departmentId
            )
            """)
    List<Doctor> findAllDoctor(Long  departmentId);

    Doctor findByUser_Id(Long userId);


}