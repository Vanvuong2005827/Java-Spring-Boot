package org.example.btvnbuoi7.domain.models.dtos.mapper;

import org.example.btvnbuoi7.domain.models.dtos.request.UserRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.UserResponse;
import org.example.btvnbuoi7.domain.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User RequestToUser(UserRequest userRequest);
    UserResponse UserToResponse(User user);
    List<UserResponse> UsersToResponse(List<User> users);
    void updateUserFromRequest(UserRequest req, @MappingTarget User user);
}
