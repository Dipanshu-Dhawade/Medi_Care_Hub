package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Patient;
import com.Hospital_Management_System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
            select exists( select 1 from Patient p where p.phoneNumber=:number)""")
    public boolean  existsByPhoneNumber(String number);
    public List<Patient> findByDeletedFalse();
    public  List<Patient> findByDeletedTrue();
    public List<Patient> findByGenderIgnoreCase(String gender);
    public List<Patient> findByGenderIgnoreCaseAndDeletedFalse(String gender);

    @Query("select u from Patient u  where u.insurance  is  not null")
    List<Patient>  existsByInsurance();

     boolean existsByEmail(String email);

    Patient findByEmail(String email);

    Patient findByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM Patient p WHERE p.user.id = :userId")
    Patient findByUser(@Param("userId") Long userId);

    void deleteByUser_id(Long userId);
}