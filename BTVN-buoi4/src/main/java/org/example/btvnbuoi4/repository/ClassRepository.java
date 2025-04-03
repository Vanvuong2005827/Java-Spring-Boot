package org.example.btvnbuoi4.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.btvnbuoi4.models.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Page<Class> findAll(Pageable pageable);
}
