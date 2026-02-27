package org.example.DTOread;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CourseReadDTO {

    private UUID id;
    private String name;
    private String description;


}
