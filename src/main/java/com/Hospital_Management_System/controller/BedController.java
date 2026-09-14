package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.BedRequestDto;
import com.Hospital_Management_System.dto.responce.BedResponceDto;
import com.Hospital_Management_System.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bed")
@RequiredArgsConstructor
public class BedController {

    private final BedService bedService;


    // CREATE
    @PostMapping("/room/{roomId}")
    public ResponseEntity<BedResponceDto> addBed(
            @PathVariable Long roomId,
            @RequestBody BedRequestDto bedRequestDto) {

        BedResponceDto response =
                bedService.addBed(roomId, bedRequestDto);

        return ResponseEntity.ok(response);
    }


    // READ - Get Bed By ID
    @GetMapping("/{bedId}")
    public ResponseEntity<BedResponceDto> getBedById(
            @PathVariable Long bedId) {

        BedResponceDto response =
                bedService.getBedById(bedId);

        return ResponseEntity.ok(response);
    }


    // READ - Get All Beds
    @GetMapping
    public ResponseEntity<List<BedResponceDto>> getAllBeds() {

        List<BedResponceDto> response =
                bedService.getAllBeds();

        return ResponseEntity.ok(response);
    }


    // UPDATE - Complete Update
    @PutMapping("/{bedId}")
    public ResponseEntity<BedResponceDto> updateBed(
            @PathVariable Long bedId,
            @RequestBody BedRequestDto bedRequestDto) {

        BedResponceDto response =
                bedService.updateBed(bedId, bedRequestDto);

        return ResponseEntity.ok(response);
    }


    // PATCH - Partial Update
    @PatchMapping("/{bedId}")
    public ResponseEntity<BedResponceDto> patchBed(
            @PathVariable Long bedId,
            @RequestBody BedRequestDto bedRequestDto) {

        BedResponceDto response =
                bedService.patchBed(bedId, bedRequestDto);

        return ResponseEntity.ok(response);
    }


    // DELETE
    @DeleteMapping("/{bedId}")
    public ResponseEntity<Boolean> deleteBed(
            @PathVariable Long bedId) {

        boolean response =
                bedService.deleteBed(bedId);

        return ResponseEntity.ok(response);
    }
}