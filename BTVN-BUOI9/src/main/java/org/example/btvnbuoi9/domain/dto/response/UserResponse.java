package org.example.btvnbuoi9.domain.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi9.domain.entities.Role;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    String username;
    String email;
    Role role;
}
