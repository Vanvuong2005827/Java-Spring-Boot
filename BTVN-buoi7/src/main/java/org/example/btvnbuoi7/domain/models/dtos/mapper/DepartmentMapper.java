package org.example.btvnbuoi7.domain.models.dtos.mapper;

import org.example.btvnbuoi7.domain.models.dtos.request.DepartmentRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.DepartmentResponse;
import org.example.btvnbuoi7.domain.models.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department RequestToDepartment (DepartmentRequest departmentRequest);
    DepartmentResponse DepartmentToResponse (Department department);
    List<DepartmentResponse> DepartmentListToResponse (List<Department> departments);
    void updateDepartmentFromRequest (DepartmentRequest departmentRequest, @MappingTarget Department department);
}
