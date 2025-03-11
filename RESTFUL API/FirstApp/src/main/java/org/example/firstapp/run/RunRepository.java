package org.example.firstapp.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<Run>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    @PostConstruct
    public void init() {
        runs.add(new Run(1, "Monday Moring Run", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 2, Location.OUTDOOR));
        runs.add(new Run(2, "Thursday Moring Run", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 2, Location.OUTDOOR));
    }
}
