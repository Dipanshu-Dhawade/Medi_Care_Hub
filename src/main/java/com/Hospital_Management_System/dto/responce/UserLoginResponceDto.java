package com.Hospital_Management_System.dto.responce;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponceDto {

       private  Long id;
              private  String username;
}
