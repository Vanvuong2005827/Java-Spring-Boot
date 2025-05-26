package org.example.btvnbuoi9.domain.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi9.domain.entities.Role;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    String username;
    String password;
    String repeatPassword;
    String email;
}
