package org.example.btvnbuoi7.domain.models.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.models.annotation.bcrypt.Bcrypt;
import org.example.btvnbuoi7.domain.models.annotation.bcrypt.BcryptEntityListener;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(BcryptEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false, unique = true, name = "username", length = 50)
    String username;

    @Column(nullable = false, name = "password", length = 255)
    @Bcrypt
    String password;

    @Column(nullable = false, unique = true, name = "email", length = 100)
    String email;

    @Column(name = "create_at")
    @CreationTimestamp
    LocalDateTime createdAt;

}
