package org.example.btvnbuoi7.domain.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.base.RestApiV1;
import org.example.btvnbuoi7.domain.models.dtos.request.DepartmentRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.DepartmentResponse;
import org.example.btvnbuoi7.domain.models.entities.Department;
import org.example.btvnbuoi7.domain.services.impl.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
@RequestMapping("/department")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentService;

    //--------------------------------getAllDepartment-----------------------------------------
    //For instance: /?page=0&size=5&sort=name,desc
    @GetMapping("/")
    public ResponseEntity<List<DepartmentResponse>> getAll(
            @PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return new ResponseEntity<>(departmentService.getAll(pageable),HttpStatus.OK);
    }

    //--------------------------------getDepartmentById-----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getById(@PathVariable long id) {
        return new ResponseEntity<>(departmentService.getById(id),HttpStatus.OK);
    }

    //--------------------------------createDepartment-----------------------------------------
    @PostMapping("/")
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(departmentService.create(departmentRequest),HttpStatus.CREATED);
    }

    //--------------------------------updateDepartment-----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable long id, @Valid @RequestBody DepartmentRequest departmentRequest) {
        return new ResponseEntity<>(departmentService.update(id, departmentRequest),HttpStatus.OK);
    }

    //--------------------------------deleteDepartmentById-----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        departmentService.delete(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }

}
