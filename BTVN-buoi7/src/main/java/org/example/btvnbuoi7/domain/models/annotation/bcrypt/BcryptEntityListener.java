package org.example.btvnbuoi7.domain.models.annotation.bcrypt;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BcryptEntityListener {
    static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PrePersist
    @PreUpdate
    public void hashFields(Object entity) {
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Bcrypt.class) && field.getType().equals(String.class)) {
                field.setAccessible(true);
                try {
                    String raw = (String) field.get(entity);
                    if (raw != null && !raw.startsWith("$2a$")) {
                        field.set(entity, encoder.encode(raw));
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
