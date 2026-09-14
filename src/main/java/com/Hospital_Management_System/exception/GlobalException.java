package com.Hospital_Management_System.exception;

import com.Hospital_Management_System.dto.error.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(PhoneNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(PhoneNotFoundException e) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().
                status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .error("Use is alredy exits")
                .build();
       return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException e) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder().
                 status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
    }
}
