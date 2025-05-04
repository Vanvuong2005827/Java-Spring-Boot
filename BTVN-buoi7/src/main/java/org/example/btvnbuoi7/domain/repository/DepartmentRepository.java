package org.example.btvnbuoi7.domain.repository;

import org.example.btvnbuoi7.domain.models.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Override
    Page<Department> findAll(Pageable pageable);
    boolean existsByNameAndIdNot(String name, long id);
}
