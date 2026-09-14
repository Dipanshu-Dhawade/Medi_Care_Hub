package com.Hospital_Management_System.repository;

import com.Hospital_Management_System.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  //  public boolean existsByPatient_IdAndDoctor_Id(Long patientId, Long doctorId);
  List<Appointment> findByPatient_IdAndDeletedFalse(Long patientId);

  @Query("""
              SELECT a
              FROM Appointment a
              JOIN a.patient p
              JOIN a.doctor d
          """)
  List<Appointment> findAppointmentsWithPatientAndDoctor();

  @Query("""
              SELECT a
              FROM Appointment a
              JOIN a.patient p
              JOIN a.doctor d
              where d.exprience_years>6
          """)
  List<Appointment> findAppointmentsDoctormorethan6();

  @Query("""
              select  a
              from Appointment a
              where a.patient.id=:patientId
          """)
  List<Appointment> findAppointments(@Param("patientId") long patientId);


  @Query("""
              SELECT YEAR(a.appointment_date),
                     MONTH(a.appointment_date),
                     COUNT(a)
              FROM Appointment a
              WHERE a.deleted = false
              GROUP BY YEAR(a.appointment_date),
                       MONTH(a.appointment_date)
              ORDER BY YEAR(a.appointment_date),
                       MONTH(a.appointment_date)
          """)
  List<Object[]> getMonthlyAppointmentCount();

  @Query("""
          SELECT a
          FROM Appointment a
          JOIN FETCH a.doctor
          JOIN FETCH a.patient
          WHERE a.id = :id
          """)
  Optional<Appointment> findAppointmentWithDoctorAndPatient(
          @Param("id") Long id
  );

  List<Appointment> findByPatient_id(Long patientId);

  List<Appointment> findBydoctor_id(Long doctorId);

    boolean existsByPatient_IdAndDoctor_Id(Long patientId, Long doctorId);
}
