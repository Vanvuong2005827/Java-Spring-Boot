package org.example.btvnbuoi7.domain.models.dtos.mapper;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.exception.extendedExceptions.ResourceNotFoundException;
import org.example.btvnbuoi7.domain.models.dtos.request.EmployeeRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.EmployeeResponse;
import org.example.btvnbuoi7.domain.models.entities.Department;
import org.example.btvnbuoi7.domain.models.entities.Employee;
import org.example.btvnbuoi7.domain.models.entities.Position;
import org.example.btvnbuoi7.domain.models.entities.User;
import org.example.btvnbuoi7.domain.repository.DepartmentRepository;
import org.example.btvnbuoi7.domain.repository.PositionRepository;
import org.example.btvnbuoi7.domain.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class EmployeeMapper {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PositionRepository positionRepository;

    public abstract List<EmployeeResponse> EmployeeToResponseList(List<Employee> employees);

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "position",   source = "positionId")
    public abstract Employee toEmployee(EmployeeRequest employeeRequest);

    public abstract EmployeeResponse toResponse(Employee employee);

    @Mapping(target = "user",       source = "userId")
    @Mapping(target = "department", source = "departmentId")
    @Mapping(target = "position",   source = "positionId")
    public abstract void updateEmployee(EmployeeRequest employeeRequest, @MappingTarget Employee employee);

    protected User map(Long userId) {
        if (userId == null) return null;
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User không tồn tại: " + userId));
    }

    protected Department mapToDepartment(Long departmentId) {
        if (departmentId == null) return null;
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department không tồn tại: " + departmentId));
    }

    protected Position mapToPosition(Long positionId) {
        if (positionId == null) return null;
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new ResourceNotFoundException("Position không tồn tại: " + positionId));
    }
}
