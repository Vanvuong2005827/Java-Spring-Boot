package org.example.btvnbuoi9.domain.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", length = 250)
    String email;

    @Column(name = "ROLE")
    Role role;
}
