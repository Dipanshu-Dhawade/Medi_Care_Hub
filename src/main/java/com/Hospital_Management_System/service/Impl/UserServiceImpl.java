package com.Hospital_Management_System.service.Impl;

import com.Hospital_Management_System.dto.request.UserSignUpRequestDto;
import com.Hospital_Management_System.dto.responce.UserLoginResponceDto;
import com.Hospital_Management_System.model.RoleTB;
import com.Hospital_Management_System.model.User;
import com.Hospital_Management_System.repository.RoleTBRepository;
import com.Hospital_Management_System.repository.UserRepository;
import com.Hospital_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleTBRepository roleTBRepository;

    private void validation(Long id ){
        if (id == null || id <= 0) {
            throw new RuntimeException("Invalid user ID");
        }
    }

    // CREATE
    @Override
    public UserLoginResponceDto signIn(UserSignUpRequestDto request) {
        if (request == null) {
            throw new RuntimeException("User request cannot be null");
        }

        if (request.getUsername() == null ||
                request.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        if (request.getPassword() == null ||
                request.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        if (request.getRole() == null ||
                request.getRole().name().trim().isEmpty()) {
            throw new RuntimeException("Role cannot be empty");
        }

        // Check username
        User existingUser =
                userRepository.findByUsername(request.getUsername());

        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }

        // Check role
        RoleTB role =
                roleTBRepository.findByRole(request.getRole());

        if (role == null) {
            throw new RuntimeException(
                    "Role is not found: " + request.getRole()
            );
        }

        // Create User
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return modelMapper.map(
                savedUser,
                UserLoginResponceDto.class
        );
    }


    // READ BY ID
    @Override
    public UserLoginResponceDto getUserById(Long id) {
        validation(id);
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User Id is not Found"));
          return  new UserLoginResponceDto(user.getId(),user.getUsername());
    }


    // READ ALL
    @Override
    public List<UserLoginResponceDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        List<UserLoginResponceDto> response = new ArrayList<>();

        for (User user : users) {

            UserLoginResponceDto dto =
                    new UserLoginResponceDto(
                            user.getId(),
                            user.getUsername()
                    );

            response.add(dto);
        }

        return response;
    }


    // UPDATE
    @Override
    public UserLoginResponceDto updateUser(
            Long id,
            UserSignUpRequestDto request) {

        validation(id);
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User is not found: " + id));

        // Validate username
        if (request.getUsername() == null ||
                request.getUsername().trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        // Check whether another user already has this username
        User existingUser =
                userRepository.findByUsername(request.getUsername());

        if (existingUser != null && !existingUser.getId().equals(id)) {
            throw new RuntimeException("Username already exists");
        }

        // Validate password
        if (request.getPassword() == null ||
                request.getPassword().trim().isEmpty()) {

            throw new RuntimeException(
                    "Password cannot be empty"
            );
        }

        // Validate role
        if (request.getRole() == null ||
                request.getRole().name().trim().isEmpty()) {

            throw new RuntimeException(
                    "Role cannot be empty"
            );
        }

        RoleTB role =
                roleTBRepository.findByRole(request.getRole());

        if (role == null) {
            throw new RuntimeException(
                    "Role is not found: " + request.getRole()
            );
        }

        // Update
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(role);

        User updatedUser =
                userRepository.save(user);

        return new UserLoginResponceDto(
                updatedUser.getId(),
                updatedUser.getUsername()
        );
    }


    // DELETE
    @Override
    public void deleteUser(Long id) {
       validation(id);
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User is not found: " + id
                        )
                );

        userRepository.delete(user);
    }
}