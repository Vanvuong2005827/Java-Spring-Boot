package org.example.btvnbuoi7.domain.services;

import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.example.btvnbuoi7.domain.models.dtos.request.EmployeeRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.EmployeeResponse;
import org.example.btvnbuoi7.domain.models.entities.Employee;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAll(
            Long userId,
            Long departmentId,
            Long positionId,
            String fullName,
            LocalDate upDateOfBirth,
            LocalDate downDateOfBirth,
            LocalDate upHiredDate,
            LocalDate downHiredDate,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            Pageable pageable
    );
    EmployeeResponse getById(@Exists(entity = Employee.class) Long id);
    EmployeeResponse save(EmployeeRequest employeeRequest);
    EmployeeResponse update(@Exists(entity = Employee.class) Long id, EmployeeRequest employeeRequest);
    void delete(@Exists(entity = Employee.class) Long id);
}
