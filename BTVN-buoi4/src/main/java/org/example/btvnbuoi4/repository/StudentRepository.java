package org.example.btvnbuoi4.repository;

import org.example.btvnbuoi4.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAll(Pageable pageable);
}
