package org.example.btvnbuoi9.domain.mappers;

import org.example.btvnbuoi9.domain.dto.response.UserResponse;
import org.example.btvnbuoi9.domain.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    List<UserResponse> toResponses(List<User> users);
}
