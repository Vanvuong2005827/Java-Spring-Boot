package org.example.btvnbuoi7.domain.services.impl;

import org.example.btvnbuoi7.domain.exception.extendedExceptions.ResourceNotFoundException;
import org.example.btvnbuoi7.domain.models.dtos.mapper.DepartmentMapper;
import org.example.btvnbuoi7.domain.models.dtos.request.DepartmentRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.DepartmentResponse;
import org.example.btvnbuoi7.domain.models.entities.Department;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.example.btvnbuoi7.domain.repository.DepartmentRepository;
import org.example.btvnbuoi7.domain.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
@Service
@Validated
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> getAll(Pageable pageable) {
        List<Department> departments = departmentRepository.findAll(pageable).getContent();
        if (departments.isEmpty()) {
            throw new ResourceNotFoundException("No department found");
        }
        return departmentMapper.DepartmentListToResponse(departments);
    }

    @Override
    public DepartmentResponse getById(@Exists(entity = Department.class) Long id) {
        Department department = departmentRepository.getById(id);
        return departmentMapper.DepartmentToResponse(department);
    }

    @Override
    public DepartmentResponse create(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.RequestToDepartment(departmentRequest);
        if (departmentRepository.existsByNameAndIdNot(department.getName(), 0)) {
            throw new DataIntegrityViolationException("Department with name " + department.getName() + " already exists");
        }
        department = departmentRepository.save(department);
        return departmentMapper.DepartmentToResponse(department);
    }

    @Override
    public DepartmentResponse update(@Exists(entity = Department.class) Long id, DepartmentRequest departmentRequest) {
        Department department = departmentRepository.getById(id);
        if (departmentRepository.existsByNameAndIdNot(department.getName(), id)) {
            throw new DataIntegrityViolationException("Department with name " + department.getName() + " already exists");
        }
        departmentMapper.updateDepartmentFromRequest(departmentRequest, department);
        department = departmentRepository.save(department);
        return departmentMapper.DepartmentToResponse(department);
    }

    @Override
    public void delete(@Exists(entity = Department.class) Long id) {
        departmentRepository.deleteById(id);
    }
}
