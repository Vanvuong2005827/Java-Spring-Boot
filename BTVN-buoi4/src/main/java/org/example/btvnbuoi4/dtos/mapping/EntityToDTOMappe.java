package org.example.btvnbuoi4.dtos.mapping;

import org.example.btvnbuoi4.dtos.ClassDTO;
import org.example.btvnbuoi4.dtos.StudentBasicDTO;
import org.example.btvnbuoi4.dtos.StudentDTO;
import org.example.btvnbuoi4.models.Class;
import org.example.btvnbuoi4.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDTOMappe {
    public static StudentDTO convertToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setDob(student.getDob());
        studentDTO.setGender(student.getGender());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhone(student.getPhone());

        //map all class to classDTO
        studentDTO.setClasses(student.getClasses().stream()
                .map(EntityToDTOMappe::convertToClassDTO)
                .collect(Collectors.toList()));
        return studentDTO;
    }

    public static ClassDTO convertToClassDTO(Class clazz) {
        ClassDTO dto = new ClassDTO();
        dto.setId(clazz.getId());
        dto.setName(clazz.getName());
        dto.setSubject(clazz.getSubject());

        List<StudentBasicDTO> studentBasicDTOS = clazz.getStudents()
                .stream()
                .map(EntityToDTOMappe::convertToStudentBasicDTO)
                .collect(Collectors.toList());
        dto.setStudents(studentBasicDTOS);
        return dto;
    }

    public static StudentBasicDTO convertToStudentBasicDTO(Student student) {
        StudentBasicDTO dto = new StudentBasicDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        return dto;
    }
}
