package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.BedRequestDto;
import com.Hospital_Management_System.dto.responce.BedResponceDto;
import com.Hospital_Management_System.enums.BedStatus;
import com.Hospital_Management_System.model.Bed;
import com.Hospital_Management_System.model.Room;
import com.Hospital_Management_System.repository.BedRepository;
import com.Hospital_Management_System.repository.RoomRepository;
import com.Hospital_Management_System.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BedServiceImpl implements BedService {

    private final BedRepository bedRepository;
    private final RoomRepository roomRepository;


    // CREATE
    @Override
    public BedResponceDto addBed(Long roomId, BedRequestDto bedRequestDto) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException("Room is not found " + roomId));

        Bed bed = new Bed();

        bed.setRoom(room);
        bed.setBedNumber(bedRequestDto.getBedNumber());
        bed.setStatus(BedStatus.Available);

        Bed savebed = bedRepository.save(bed);

        return new BedResponceDto(
                savebed.getBedNumber(),
                savebed.getStatus()
        );
    }


    // READ - Get Bed By ID
    @Override
    public BedResponceDto getBedById(Long bedId) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new RuntimeException("Bed is not found " + bedId));

        return new BedResponceDto(
                bed.getBedNumber(),
                bed.getStatus()
        );
    }


    // READ - Get All Beds
    @Override
    public List<BedResponceDto> getAllBeds() {

        List<Bed> beds = bedRepository.findAll();

        if (beds.isEmpty()) {
            throw new RuntimeException("No beds found");
        }

        return beds.stream()
                .map(bed -> new BedResponceDto(
                        bed.getBedNumber(),
                        bed.getStatus()
                ))
                .toList();
    }


    // UPDATE - Complete Update
    @Override
    public BedResponceDto updateBed(
            Long bedId,
            BedRequestDto bedRequestDto) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new RuntimeException("Bed is not found " + bedId));

        bed.setBedNumber(bedRequestDto.getBedNumber());

        // If you don't want the status to be changed during update,
        // keep the existing status.
        bed.setStatus(bed.getStatus());

        Bed updatedBed = bedRepository.save(bed);

        return new BedResponceDto(
                updatedBed.getBedNumber(),
                updatedBed.getStatus()
        );
    }


    // PATCH - Partial Update
    @Override
    public BedResponceDto patchBed(
            Long bedId,
            BedRequestDto bedRequestDto) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new RuntimeException("Bed is not found " + bedId));

        if (bedRequestDto.getBedNumber() != null) {
            bed.setBedNumber(bedRequestDto.getBedNumber());
        }

        Bed updatedBed = bedRepository.save(bed);

        return new BedResponceDto(
                updatedBed.getBedNumber(),
                updatedBed.getStatus()
        );
    }


    // DELETE
    @Override
    public boolean deleteBed(Long bedId) {

        Bed bed = bedRepository.findById(bedId)
                .orElseThrow(() ->
                        new RuntimeException("Bed is not found " + bedId));

        bedRepository.delete(bed);

        return true;
    }
}