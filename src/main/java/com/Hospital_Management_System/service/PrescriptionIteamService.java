package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.PrescriptionIteamRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionIteamResponceDto;
import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.model.PrescriptionIteam;
import org.json.simple.JSONObject;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PrescriptionIteamService {
    // CREATE
    public List<PrescriptionIteam> add(Long prescriptionId, JSONObject jsonObject);

    // READ - get all items
    List<PrescriptionIteamResponceDto> getAll();

    // READ - get one item by ID
    PrescriptionIteamResponceDto getById(Long id);

    // READ - get medicines by prescription ID
    List<Medicine> getALLMedicineByPrecriptionId(Long prescriptionId);

    // Complete  UPDATE
    PrescriptionIteamResponceDto update(Long id, PrescriptionIteamRequestDto prescriptionIteamRequestDto);

    // Complete  UPDATE
    PrescriptionIteamResponceDto partialUpdate(Long id, PrescriptionIteamRequestDto prescriptionIteamRequestDto);


    // DELETE
    void delete(Long id);
}
