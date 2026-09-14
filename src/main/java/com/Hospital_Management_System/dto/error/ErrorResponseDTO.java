package com.Hospital_Management_System.dto.error;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDTO {

    private int status;
    private String message;
    private String error;

}