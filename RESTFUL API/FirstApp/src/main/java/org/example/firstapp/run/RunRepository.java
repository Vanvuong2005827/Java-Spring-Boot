package org.example.firstapp.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<Run>();

    private static final Logger logger = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select id, title, started_on, completed_on, miles, location FROM Run where id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, started_on, completed_on, miles, location) values (?, ?, ?, ?, ?, ?)")
                .param(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();

            Assert.state(updated == 1, "Fail to create run" + run.title());
    }

    public void update(Run run, Integer id) {
        var update = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .param(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id))
                .update();

        Assert.state(update == 1, "Fail to update run" + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();
    }

    public int count() {
        return jdbcClient.sql("select  * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select  * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
//    public List<Run> findAll() {
//        return runs;
//    }
//
//    public Optional<Run> findById(Integer id) {
//        return runs.stream()
//                .filter(run -> run.id() == id)
//                .findFirst();
//    }
//
//    public void create(Run run) {
//        runs.add(run);
//    }
//
//    public void update(Run run, Integer id) {
//        Optional<Run> found = findById(id);
//        if (found.isPresent()) {
//            runs.set(runs.indexOf(found.get()), run);
//        }
//    }
//
//    public void delete(Integer id) {
//        runs.removeIf(run -> run.id().equals(id));
//    }
//
//
//    @PostConstruct
//    public void init() {
//        runs.add(new Run(1, "Monday Moring Run", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 2, Location.OUTDOOR));
//        runs.add(new Run(2, "Thursday Moring Run", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), 2, Location.OUTDOOR));
//    }
}
