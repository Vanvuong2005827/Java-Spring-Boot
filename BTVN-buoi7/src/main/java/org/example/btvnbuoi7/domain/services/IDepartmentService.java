package org.example.btvnbuoi7.domain.services;

import org.example.btvnbuoi7.domain.models.dtos.request.DepartmentRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.DepartmentResponse;
import org.example.btvnbuoi7.domain.models.entities.Department;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAll(Pageable pageable);
    DepartmentResponse getById(@Exists(entity = Department.class) Long id);
    DepartmentResponse create(DepartmentRequest departmentRequest);
    DepartmentResponse update(@Exists(entity = Department.class) Long id, DepartmentRequest departmentRequest);
    void delete(@Exists(entity = Department.class) Long id);

}
