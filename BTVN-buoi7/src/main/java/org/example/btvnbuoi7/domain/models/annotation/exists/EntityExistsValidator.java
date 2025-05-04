package org.example.btvnbuoi7.domain.models.annotation.exists;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityExistsValidator implements ConstraintValidator<Exists, Object> {

    @PersistenceContext
    EntityManager em;
    Class<?> entityClass;
    String fieldName;
    String message;

    @Override
    public void initialize(Exists constraint) {
        entityClass = constraint.entity();
        fieldName   = constraint.fieldName();
        message     = constraint.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true;
        String jpql = String.format(
                "SELECT COUNT(e) FROM %s e WHERE e.%s = :val",
                entityClass.getSimpleName(), fieldName
        );

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("val", value)
                .getSingleResult();

        if (count == 0) {
            return false;
        }
        return true;
    }
}
