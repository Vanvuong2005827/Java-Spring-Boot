package org.example.btvnbuoi3.respository;

import org.example.btvnbuoi3.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
}
