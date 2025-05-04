package org.example.btvnbuoi7.domain.models.dtos.response;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    UserResponse user;

    DepartmentResponse department;

    PositionResponse position;

    String fullName;

    LocalDate dateOfBirth;

    LocalDate hiredDate;

    BigDecimal salary;
}
