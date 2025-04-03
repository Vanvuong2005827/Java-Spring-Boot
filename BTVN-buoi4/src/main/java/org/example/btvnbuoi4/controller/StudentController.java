package org.example.btvnbuoi4.controller;

import org.example.btvnbuoi4.dtos.StudentDTO;
import org.example.btvnbuoi4.dtos.mapping.EntityToDTOMappe;
import org.example.btvnbuoi4.exceptions.ResourceNotFoundException;
import org.example.btvnbuoi4.models.Student;
import org.example.btvnbuoi4.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //get
    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction dir
    ) {
        Sort sorted = Sort.by(dir, sort);
        List<Student> list = studentService.getAllStudents(0, 100000, sorted);
        List<StudentDTO> dtos = list.stream()
                .map(EntityToDTOMappe::convertToStudentDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            throw new ResourceNotFoundException("Không tìm thấy sinh viên với id: " + id);
        }
        return new ResponseEntity<>(EntityToDTOMappe.convertToStudentDTO(student), HttpStatus.OK);
    }

    //post
    @PostMapping("")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    //put
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        student.setId(id);
        Student student1 = studentService.getStudentById(id);
        if (student1 == null) {
            throw new ResourceNotFoundException("Không tìm thấy sinh viên với id: " + id);
        }
        return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Xóa thành công" ,HttpStatus.NO_CONTENT);
    }

}
