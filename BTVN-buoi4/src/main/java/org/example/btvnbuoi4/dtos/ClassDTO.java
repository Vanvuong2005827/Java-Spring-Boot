package org.example.btvnbuoi4.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDTO {
    private int id;
    private String name;
    private String subject;
    private List<StudentBasicDTO> students;


}
