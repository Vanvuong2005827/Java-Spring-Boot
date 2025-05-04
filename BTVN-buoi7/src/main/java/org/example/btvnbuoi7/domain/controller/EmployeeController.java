package org.example.btvnbuoi7.domain.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.base.RestApiV1;
import org.example.btvnbuoi7.domain.models.dtos.request.EmployeeRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.EmployeeResponse;
import org.example.btvnbuoi7.domain.models.entities.Employee;
import org.example.btvnbuoi7.domain.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestApiV1
@RequestMapping("/employee")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    //--------------------------------getAllEmployee-----------------------------------------
    //For instance /?
    //  userId=1&
    //  departmentId=2&
    //  positionId=4&
    //  fullName=Vuong&
    //  upDateOfBirth=1990-01-01&
    //  downDateOfBirth=2000-12-31&
    //  upHiredDate=2020-01-01&
    //  downHiredDate=2025-12-31&
    //  minSalary=5000&
    //  maxSalary=20000&
    //  page=0&
    //  size=10&
    //  sort=fullName,asc&
    //  sort=salary,desc

    // Or
    // http://localhost:8080/api/v1/employee/?userId=5&departmentId=1&positionId=3&fullName=vuong&upDateOfBirth=1990-01-01&downDateOfBirth=2025-12-31&upHiredDate=2020-01-01&downHiredDate=2025-12-31&minSalary=5000&maxSalary=20000&page=0 &size=10&sort=fullName,asc&sort=salary,desc
    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(
            @RequestParam(value = "userId",           required = false) Long userId,
            @RequestParam(value = "departmentId",     required = false) Long departmentId,
            @RequestParam(value = "positionId",       required = false) Long positionId,
            @RequestParam(value = "fullName",         required = false) String fullName,
            @RequestParam(value = "upDateOfBirth",    required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate upDateOfBirth,
            @RequestParam(value = "downDateOfBirth",  required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate downDateOfBirth,
            @RequestParam(value = "upHiredDate",      required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate upHiredDate,
            @RequestParam(value = "downHiredDate",    required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate downHiredDate,
            @RequestParam(value = "minSalary",        required = false) BigDecimal minSalary,
            @RequestParam(value = "maxSalary",        required = false) BigDecimal maxSalary,
            @PageableDefault(page = 0, size = 20,
                    sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        List<EmployeeResponse> employees = employeeService.getAll(
                userId, departmentId, positionId, fullName,
                upDateOfBirth, downDateOfBirth, upHiredDate, downHiredDate,
                minSalary, maxSalary,
                pageable
        );
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //--------------------------------getEmployeeById-----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
    }

    //--------------------------------createEmployee-----------------------------------------
    @PostMapping("/")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.save(employeeRequest), HttpStatus.CREATED);
    }

    //--------------------------------updateEmployee-----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable long id, @RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.update(id, employeeRequest), HttpStatus.OK);
    }

    //--------------------------------deleteEmployeeById-----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
}
