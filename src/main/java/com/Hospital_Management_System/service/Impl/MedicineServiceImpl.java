package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.MedicineRequestDto;
import com.Hospital_Management_System.dto.responce.MedicineResponceDto;
import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.repository.MedicineRepository;
import com.Hospital_Management_System.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;


    // ADD MULTIPLE MEDICINES
    @Override
    public boolean addMultipleMedicine(List<MedicineRequestDto> medicines) {

        if (medicines == null || medicines.isEmpty()) {
            throw new RuntimeException("Medicine list is empty");
        }

        List<Medicine> medicineList = new ArrayList<>();

        for (MedicineRequestDto dto : medicines) {

            Medicine medicine = new Medicine();

            medicine.setName(dto.getName());
            medicine.setPrice(dto.getPrice());
            medicine.setCategoary(dto.getCategoary());
            medicine.setUnit(dto.getUnit());

            medicineList.add(medicine);
        }

        List<Medicine> savedMedicines =
                medicineRepository.saveAll(medicineList);

        return !savedMedicines.isEmpty();
    }


    // ADD SINGLE MEDICINE
    @Override
    public boolean addMedicine(MedicineRequestDto dto) {

        if (dto == null) {
            throw new RuntimeException("Medicine data cannot be null");
        }

        Medicine medicine = new Medicine();

        medicine.setName(dto.getName());
        medicine.setPrice(dto.getPrice());
        medicine.setCategoary(dto.getCategoary());
        medicine.setUnit(dto.getUnit());

        Medicine savedMedicine =
                medicineRepository.save(medicine);

        return savedMedicine.getId() != null;
    }


    // GET MEDICINE BY ID
    @Override
    public MedicineResponceDto getMedicineById(Long id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicine not found with id: " + id
                        )
                );

        return convertToResponseDto(medicine);
    }


    // GET ALL MEDICINES
    @Override
    public List<MedicineResponceDto> getAllMedicine() {

        List<Medicine> medicines = medicineRepository.findAll();

        if (medicines.isEmpty()) {
            throw new RuntimeException("No medicines found");
        }

        List<MedicineResponceDto> responseList = new ArrayList<>();

        for (Medicine medicine : medicines) {
            responseList.add(
                    convertToResponseDto(medicine)
            );
        }

        return responseList;
    }


    // COMPLETE UPDATE
    // @Override
    public MedicineResponceDto updateMedicine(
            Long id,
            MedicineRequestDto dto) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicine not found with id: " + id
                        )
                );

        medicine.setName(dto.getName());
        medicine.setPrice(dto.getPrice());
        medicine.setCategoary(dto.getCategoary());
        medicine.setUnit(dto.getUnit());

        Medicine updatedMedicine =
                medicineRepository.save(medicine);

        return convertToResponseDto(updatedMedicine);
    }


    // DELETE MEDICINE
    @Override
    public boolean deleteMedicine(Long id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Medicine not found with id: " + id
                        )
                );

        medicineRepository.delete(medicine);

        return true;
    }

    // ENTITY → RESPONSE DTO
    private MedicineResponceDto convertToResponseDto(
            Medicine medicine) {

        MedicineResponceDto dto = new MedicineResponceDto();


        dto.setName(medicine.getName());
        dto.setPrice(medicine.getPrice());
        dto.setCategoary(medicine.getCategoary());
        dto.setUnit(medicine.getUnit());

        return dto;
    }
}