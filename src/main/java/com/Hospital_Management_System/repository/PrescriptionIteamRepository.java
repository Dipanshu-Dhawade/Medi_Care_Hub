package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Medicine;
import com.Hospital_Management_System.model.PrescriptionIteam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionIteamRepository extends JpaRepository<PrescriptionIteam, Long> {


    @Query("""
            select  pi.medicine
            from PrescriptionIteam pi
            where pi.prescription.id=:precriptionId
            """)
    public List<Medicine> getALLMedicineByPrecriptionId(Long precriptionId);

}