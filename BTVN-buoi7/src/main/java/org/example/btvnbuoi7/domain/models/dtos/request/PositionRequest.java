package org.example.btvnbuoi7.domain.models.dtos.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionRequest {
    @Size(max = 100, message = "name must not be longer than 100 characters")
    @NotBlank(message = "name must not be blank")
    String name;

    @Nationalized
    String description;
}
