package org.example.btvnbuoi7.domain.services.specifications;


import jakarta.persistence.criteria.Predicate;
import org.example.btvnbuoi7.domain.models.entities.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeeSpecification {
    public static Specification<Employee> filter(
            Long userId,
            Long departmentId,
            Long positionId,
            String fullName,
            LocalDate upDateOfBirth,
            LocalDate downDateOfBirth,
            LocalDate upHiredDate,
            LocalDate downHiredDate,
            BigDecimal minSalary,
            BigDecimal maxSalary
    ) {
        return (root, query, cb) -> {
            List<Predicate> preds = new ArrayList<>();

            if (userId != null) {
                preds.add(cb.equal(root.get("user").get("id"), userId));
            }
            if (departmentId != null) {
                preds.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            if (positionId != null) {
                preds.add(cb.equal(root.get("position").get("id"), positionId));
            }
            if (fullName != null && !fullName.isBlank()) {
                preds.add(cb.like(cb.lower(root.get("fullName")),
                        "%" + fullName.toLowerCase() + "%"));
            }
            if (minSalary != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("salary"), minSalary));
            }
            if (maxSalary != null) {
                preds.add(cb.lessThanOrEqualTo(root.get("salary"), maxSalary));
            }
            if (upDateOfBirth != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("dateOfBirth"), upDateOfBirth));
            }

            if (downDateOfBirth != null) {
                preds.add(cb.lessThanOrEqualTo(root.get("dateOfBirth"), downDateOfBirth));
            }

            if (upHiredDate != null) {
                preds.add(cb.greaterThanOrEqualTo(root.get("hiredDate"), upHiredDate));
            }

            if (downHiredDate != null) {
                preds.add(cb.lessThanOrEqualTo(root.get("hiredDate"), downHiredDate));
            }

            return cb.and(preds.toArray(new Predicate[0]));
        };
    }
}
