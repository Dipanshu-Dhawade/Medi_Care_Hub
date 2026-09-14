package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.PrescriptionIteamRequestDto;
import com.Hospital_Management_System.dto.responce.PrescriptionIteamResponceDto;
import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.model.Prescription;
import com.Hospital_Management_System.model.PrescriptionIteam;
import com.Hospital_Management_System.repository.MedicineRepository;
import com.Hospital_Management_System.repository.PrescriptionIteamRepository;
import com.Hospital_Management_System.repository.PrescriptionRepository;
import com.Hospital_Management_System.service.PrescriptionIteamService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionIteamServiceImpl
        implements PrescriptionIteamService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionIteamRepository prescriptionIteamRepository;


    // ==========================================
    // CREATE - ADD MULTIPLE PRESCRIPTION ITEMS
    // ==========================================
    @Override
    public List<PrescriptionIteam> add(
            Long prescriptionId,
            JSONObject jsonObject) {

        Prescription prescription =
                prescriptionRepository.findById(prescriptionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription not found: "
                                                + prescriptionId
                                ));

        JSONArray medicines =
                (JSONArray) jsonObject.get("medicines");

        if (medicines == null || medicines.isEmpty()) {
            throw new RuntimeException("Medicine list is empty");
        }

        List<PrescriptionIteam> items = new ArrayList<>();

        for (Object obj : medicines) {

            JSONObject medicineJson = (JSONObject) obj;

            Long medicineId = Long.parseLong(
                    medicineJson.get("medicineId").toString()
            );

            Medicine medicine =
                    medicineRepository.findById(medicineId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Medicine not found: "
                                                    + medicineId
                                    ));

            PrescriptionIteam item =
                    new PrescriptionIteam();

            item.setMedicine(medicine);
            item.setPrescription(prescription);

            item.setDosage(
                    medicineJson.get("dosage").toString()
            );

            item.setFrequency(
                    medicineJson.get("frequency").toString()
            );

            item.setDuration(
                    medicineJson.get("duration").toString()
            );

            items.add(item);
        }

        return prescriptionIteamRepository.saveAll(items);
    }


    // ==========================================
    // READ - GET ALL
    // ==========================================
    @Override
    public List<PrescriptionIteamResponceDto> getAll() {

        List<PrescriptionIteam> items =
                prescriptionIteamRepository.findAll();

        if (items.isEmpty()) {
            throw new RuntimeException(
                    "No prescription items found"
            );
        }

        List<PrescriptionIteamResponceDto> responseList =
                new ArrayList<>();

        for (PrescriptionIteam item : items) {
            responseList.add(
                    convertToResponseDto(item)
            );
        }

        return responseList;
    }


    // ==========================================
    // READ - GET BY ID
    // ==========================================
    @Override
    public PrescriptionIteamResponceDto getById(Long id) {

        PrescriptionIteam item =
                prescriptionIteamRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription item not found: "
                                                + id
                                ));

        return convertToResponseDto(item);
    }


    // ==========================================
    // READ - GET MEDICINES BY PRESCRIPTION ID
    // ==========================================
    @Override
    public List<Medicine> getALLMedicineByPrecriptionId(
            Long prescriptionId) {

        List<Medicine> medicines =
                prescriptionIteamRepository
                        .getALLMedicineByPrecriptionId(
                                prescriptionId
                        );

        if (medicines.isEmpty()) {
            throw new RuntimeException(
                    "No medicines found for prescription: "
                            + prescriptionId
            );
        }

        return medicines;
    }


    // ==========================================
    // COMPLETE UPDATE
    // ==========================================
    @Override
    public PrescriptionIteamResponceDto update(
            Long id,
            PrescriptionIteamRequestDto dto) {

        PrescriptionIteam item =
                prescriptionIteamRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription item not found: "
                                                + id
                                ));

        if (dto.getMedicineid() == null) {
            throw new RuntimeException(
                    "Medicine ID is required"
            );
        }

        Medicine medicine =
                medicineRepository.findById(
                                dto.getMedicineid()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Medicine not found: "
                                                + dto.getMedicineid()
                                ));

        item.setMedicine(medicine);
        item.setDosage(dto.getDosage());
        item.setFrequency(dto.getFrequency());
        item.setDuration(dto.getDuration());

        PrescriptionIteam updatedItem =
                prescriptionIteamRepository.save(item);

        return convertToResponseDto(updatedItem);
    }


    // ==========================================
    // PARTIAL UPDATE
    // ==========================================
    @Override
    public PrescriptionIteamResponceDto partialUpdate(
            Long id,
            PrescriptionIteamRequestDto dto) {

        PrescriptionIteam item =
                prescriptionIteamRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription item not found: "
                                                + id
                                ));


        // Medicine
        if (dto.getMedicineid() != null) {

            Medicine medicine =
                    medicineRepository.findById(
                                    dto.getMedicineid()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Medicine not found: "
                                                    + dto.getMedicineid()
                                    ));

            item.setMedicine(medicine);
        }


        // Dosage
        if (dto.getDosage() != null) {
            item.setDosage(dto.getDosage());
        }


        // Frequency
        if (dto.getFrequency() != null) {
            item.setFrequency(dto.getFrequency());
        }


        // Duration
        if (dto.getDuration() != null) {
            item.setDuration(dto.getDuration());
        }


        PrescriptionIteam updatedItem =
                prescriptionIteamRepository.save(item);

        return convertToResponseDto(updatedItem);
    }


    // ==========================================
    // DELETE
    // ==========================================
    @Override
    public void delete(Long id) {

        PrescriptionIteam item =
                prescriptionIteamRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Prescription item not found: "
                                                + id
                                ));

        prescriptionIteamRepository.delete(item);
    }


    // ==========================================
    // ENTITY → RESPONSE DTO
    // ==========================================
    private PrescriptionIteamResponceDto convertToResponseDto(
            PrescriptionIteam item) {

        PrescriptionIteamResponceDto dto =
                new PrescriptionIteamResponceDto();

        dto.setId(item.getId());
        dto.setDosage(item.getDosage());
        dto.setFrequency(item.getFrequency());
        dto.setDuration(item.getDuration());

        if (item.getMedicine() != null) {
            dto.setMedicineid(
                    item.getMedicine().getId()
            );

            dto.setMedicineName(
                    item.getMedicine().getName()
            );
        }
        return dto;
    }
}