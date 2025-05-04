package org.example.btvnbuoi7.domain.services;

import org.example.btvnbuoi7.domain.models.dtos.request.UserRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.UserResponse;
import org.example.btvnbuoi7.domain.models.entities.User;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Validated
public interface IUserService {
    List<UserResponse> getAll(Pageable pageable);
    UserResponse getById(@Exists(entity = User.class, message = "No user found") long id);
    UserResponse create(UserRequest employeeRequest);
    UserResponse update(@Exists(entity = User.class, message = "No user found") long id,UserRequest employeeRequest);
    void deleteById(@Exists(entity = User.class, message = "No user found") long id);
}
