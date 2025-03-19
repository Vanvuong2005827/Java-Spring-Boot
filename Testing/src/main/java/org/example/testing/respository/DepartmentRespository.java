package org.example.testing.respository;

import org.example.testing.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRespository extends JpaRepository<Department, Integer> {
}
