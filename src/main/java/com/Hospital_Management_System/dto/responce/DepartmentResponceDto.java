package com.Hospital_Management_System.dto.responce;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DepartmentResponceDto {

    private Long id;

    private String name;

    private LocalDateTime createAt;
}
