package org.example.btvnbuoi7.domain.repository;

import org.example.btvnbuoi7.domain.models.entities.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    @Override
    Page<Position> findAll(Pageable pageable);
    boolean existsByNameAndIdNot(String name, long id);
}
