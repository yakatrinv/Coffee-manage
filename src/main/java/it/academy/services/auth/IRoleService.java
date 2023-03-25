package it.academy.services.auth;

import it.academy.dto.auth.UserDto;

public interface IRoleService {
    UserDto createUser(UserDto addressDto);
}
