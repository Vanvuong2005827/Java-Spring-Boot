package org.example.btvnbuoi4.controller;


import jakarta.validation.Valid;
import org.example.btvnbuoi4.dtos.ClassDTO;
import org.example.btvnbuoi4.dtos.mapping.EntityToDTOMappe;
import org.example.btvnbuoi4.exceptions.ResourceNotFoundException;
import org.example.btvnbuoi4.models.Class;
import org.example.btvnbuoi4.services.ClassService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    //get
    @GetMapping("")
    public ResponseEntity<List<ClassDTO>> getAllClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction dir
    ) {
        Sort sorted = Sort.by(dir, sort);
        List<Class> list = classService.getAllClasses(page, size, sorted);

        List<ClassDTO> classDTO = list.stream()
                .map(EntityToDTOMappe::convertToClassDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getClassById(@PathVariable("id") int id) {
        Class foundClass = classService.getClassById(id);
        if (foundClass == null) {
            throw new ResourceNotFoundException("Không tìm thấy lớp với id: " + id);
        }
        return new ResponseEntity<>(EntityToDTOMappe.convertToClassDTO(foundClass), HttpStatus.OK);
    }

    //post
    @PostMapping("")
    public ResponseEntity<Class> createClass(@Valid @RequestBody Class newClass) {
        return new ResponseEntity<>(classService.saveClass(newClass), HttpStatus.CREATED);
    }

    //put
    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@Valid @PathVariable("id") int id, @RequestBody Class newClass) {
        newClass.setId(id);
        Class currentClass = classService.getClassById(id);
        if (currentClass == null) {
            throw new ResourceNotFoundException("Không tìm thấy lớp với id: " + id);
        }
        return new ResponseEntity<>(classService.saveClass(newClass), HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Class> deleteClass(@PathVariable("id") int id) {
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
