package org.example.btvnbuoi9.service;

import org.example.btvnbuoi9.domain.dto.response.UserResponse;

import java.util.List;

public interface IAdminService {
    List<UserResponse> getUsers();
}
