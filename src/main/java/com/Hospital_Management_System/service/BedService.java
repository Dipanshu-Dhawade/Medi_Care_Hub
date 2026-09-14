package com.Hospital_Management_System.service;

import com.Hospital_Management_System.dto.request.BedRequestDto;
import com.Hospital_Management_System.dto.responce.BedResponceDto;
import com.Hospital_Management_System.model.Bed;

import java.util.List;

public interface BedService {

    // CREATE
    BedResponceDto addBed(Long roomId, BedRequestDto bedRequestDto);

    // READ - single bed
    BedResponceDto getBedById(Long bedId);

    // READ - all beds
    List<BedResponceDto> getAllBeds();

    // UPDATE
    BedResponceDto updateBed(Long bedId, BedRequestDto bedRequestDto);

    // PATCH
    BedResponceDto patchBed(Long bedId, BedRequestDto bedRequestDto);

    // DELETE
    boolean deleteBed(Long bedId);
}