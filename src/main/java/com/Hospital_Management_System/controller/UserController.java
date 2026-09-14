package com.Hospital_Management_System.controller;

import com.Hospital_Management_System.dto.request.UserSignUpRequestDto;
import com.Hospital_Management_System.dto.responce.UserLoginResponceDto;
import com.Hospital_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // CREATE
    @PostMapping
    public ResponseEntity<UserLoginResponceDto> createUser(
            @RequestBody UserSignUpRequestDto request) {

        UserLoginResponceDto response =
                userService.signIn(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserLoginResponceDto> getUserById(
            @PathVariable Long id) {

        UserLoginResponceDto response =
                userService.getUserById(id);

        return ResponseEntity.ok(response);
    }


    // READ ALL
    @GetMapping
    public ResponseEntity<List<UserLoginResponceDto>> getAllUsers() {

        List<UserLoginResponceDto> response =
                userService.getAllUsers();

        return ResponseEntity.ok(response);
    }


    // UPDATE
        @PutMapping("/{id}")
    public ResponseEntity<UserLoginResponceDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserSignUpRequestDto request) {

        UserLoginResponceDto response =
                userService.updateUser(id, request);

        return ResponseEntity.ok(response);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User is delete successfully");
    }
}