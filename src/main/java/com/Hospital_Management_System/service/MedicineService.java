package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.MedicineRequestDto;
import com.Hospital_Management_System.dto.responce.MedicineResponceDto;
import com.Hospital_Management_System.model.Medicine;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MedicineService {

    // CREATE
    boolean addMedicine(MedicineRequestDto medicine);

    // CREATE MULTIPLE
    boolean addMultipleMedicine(List<MedicineRequestDto> medicines);

    // READ ONE
    MedicineResponceDto getMedicineById(Long id);

    // READ ALL
    List<MedicineResponceDto> getAllMedicine();

    // UPDATE
    MedicineResponceDto updateMedicine(Long id, MedicineRequestDto medicine);

    // DELETE
    boolean deleteMedicine(Long id);
}