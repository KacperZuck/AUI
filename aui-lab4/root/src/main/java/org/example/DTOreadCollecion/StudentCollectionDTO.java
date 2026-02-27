package org.example.DTOreadCollecion;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class StudentCollectionDTO {

    private UUID id;
    private String fullname;

}
