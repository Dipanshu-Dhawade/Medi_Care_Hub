package com.Hospital_Management_System.scheduler;

import com.Hospital_Management_System.enums.AdmissionStatus;
import com.Hospital_Management_System.enums.BedStatus;
import com.Hospital_Management_System.model.Admission;
import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.repository.AdmissionRepository;
import com.Hospital_Management_System.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BedScheduler {

    private final AdmissionRepository admissionRepository;
    private final BedRepository bedRepository;

    @Scheduled(fixedRate = 10000) // every 10 seconds
    @Transactional
    public void assignWaitingPatient() {
           log.info("it is checking ");
        LocalDateTime now = LocalDateTime.now();

        List<Admission> activeAdmissions =
                admissionRepository.findByStatusEnums(
                        AdmissionStatus.Active
                );

        for (Admission activeAdmission : activeAdmissions) {
            if (activeAdmission.getDischarge() != null &&
                    activeAdmission.getDischarge()
                            .getDischargeDate()
                            .isBefore(now)) {

                Bed bed = activeAdmission.getBed();

                // Current patient discharged
                activeAdmission.setStatusEnums(
                        AdmissionStatus.Discharged
                );

                admissionRepository.save(activeAdmission);

                if (bed == null) {
                    continue;
                }

                /*
                 * Find waiting patients for this bed
                 */
                List<Admission> waitingAdmissions =
                        admissionRepository
                                .findByBedIdAndStatusEnumsOrderByAdmissionAsc(
                                        bed.getId(),
                                        AdmissionStatus.Waiting
                                );

                if (!waitingAdmissions.isEmpty()) {

                    // Oldest waiting patient
                    Admission waitingAdmission =
                            waitingAdmissions.get(0);

                    waitingAdmission.setStatusEnums(
                            AdmissionStatus.Active
                    );
                    waitingAdmission.setBed(bed);
                    admissionRepository.save(waitingAdmission);
                    // Bed remains occupied
                    bed.setStatus(BedStatus.Occupied);

                } else {
                    // Nobody waiting
                    bed.setStatus(BedStatus.Available);
                }
                bedRepository.save(bed);
            }
        }
    }
}