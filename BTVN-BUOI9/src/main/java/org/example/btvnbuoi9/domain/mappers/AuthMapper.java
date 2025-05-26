package org.example.btvnbuoi9.domain.mappers;

import org.example.btvnbuoi9.constants.ErrorMessage;
import org.example.btvnbuoi9.domain.dto.request.UserRegisterRequest;
import org.example.btvnbuoi9.domain.dto.response.UserRegisterResponse;
import org.example.btvnbuoi9.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User toUser(UserRegisterRequest userRegisterRequest);
    UserRegisterResponse toUserRegisterResponse(User user);
}
