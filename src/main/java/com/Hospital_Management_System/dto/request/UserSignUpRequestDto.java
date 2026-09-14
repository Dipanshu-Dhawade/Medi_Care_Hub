package com.Hospital_Management_System.dto.request;

import com.Hospital_Management_System.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequestDto {
    private  String username;
    private  String password;
    private  String email;
    private Role role;
}
