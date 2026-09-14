package com.Hospital_Management_System.service;


import com.Hospital_Management_System.dto.request.UserSignUpRequestDto;
import com.Hospital_Management_System.dto.responce.UserLoginResponceDto;

import java.util.List;

public interface UserService {

    public UserLoginResponceDto signIn(UserSignUpRequestDto request);

    UserLoginResponceDto getUserById(Long id);

    List<UserLoginResponceDto> getAllUsers();

    // Update
    UserLoginResponceDto updateUser(Long id, UserSignUpRequestDto userSignUpRequestDto);

    // Delete
    void deleteUser(Long id);
}
