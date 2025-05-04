package org.example.btvnbuoi7.domain.models.dtos.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EmployeeRequest {
    @NotNull(message = "userId không được null")
    Long userId;

    @NotNull(message = "departmentId không được null")
    Long departmentId;

    @NotNull(message = "positionId không được null")
    Long positionId;

    @NotBlank(message = "fullName không được bỏ trống")
    @Size(max = 100)
    String fullName;

    @Past(message = "dateOfBirth phải là ngày trong quá khứ")
    LocalDate dateOfBirth;

    @NotNull(message = "hiredDate không được null")
    LocalDate hiredDate;

    @NotNull(message = "salary không được null")
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal salary;
}