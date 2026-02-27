package org.example.DTOread;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StudentReadDTO {

    private UUID id;
    private String fullname;
    private double grade;
    private String courseName;
}
