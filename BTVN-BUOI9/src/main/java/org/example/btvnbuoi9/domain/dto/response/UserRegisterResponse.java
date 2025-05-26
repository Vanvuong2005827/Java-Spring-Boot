package org.example.btvnbuoi9.domain.dto.response;

import lombok.*;
import org.example.btvnbuoi9.domain.entities.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterResponse {
    String username;
    String password;
    String email;
    String role;
}
