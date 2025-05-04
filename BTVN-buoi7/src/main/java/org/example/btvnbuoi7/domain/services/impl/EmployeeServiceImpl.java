package org.example.btvnbuoi7.domain.services.impl;

import org.example.btvnbuoi7.domain.exception.extendedExceptions.ResourceNotFoundException;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.example.btvnbuoi7.domain.models.dtos.mapper.EmployeeMapper;
import org.example.btvnbuoi7.domain.models.dtos.request.EmployeeRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.EmployeeResponse;
import org.example.btvnbuoi7.domain.models.entities.Employee;
import org.example.btvnbuoi7.domain.repository.EmployeeRepository;
import org.example.btvnbuoi7.domain.services.IEmployeeService;
import org.example.btvnbuoi7.domain.services.specifications.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
@Validated
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponse> getAll(
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
    ) {
        Specification<Employee> spec = EmployeeSpecification.filter(
                userId, departmentId, positionId, fullName, upDateOfBirth, downDateOfBirth, upHiredDate, downHiredDate, minSalary, maxSalary
        );

        List<Employee> employees = employeeRepository.findAll(spec, pageable).getContent();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employee found");
        }
        return employeeMapper.EmployeeToResponseList(employees);
    }

    @Override
    public EmployeeResponse getById(@Exists(entity = Employee.class) Long id) {
        Employee employee = employeeRepository.getById(id);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse save(EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse update(@Exists(entity = Employee.class) Long id, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.getById(id);
        employeeMapper.updateEmployee(employeeRequest, employee);
        employee = employeeRepository.save(employee);
        return employeeMapper.toResponse(employee);
    }

    @Override
    public void delete(@Exists(entity = Employee.class) Long id) {
        employeeRepository.deleteById(id);
    }
}
