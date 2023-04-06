package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.models.auth.User;
import it.academy.models.pageable.Pageable;

import java.util.Set;

public interface IUserService {
    UserDto findAuthUser(String login, String password);

    UserDto findUserByLogin(String login);

    User createUser(String login, String password, Set<RoleDto> roles);

    void updatePassUser(String login, String password, String oldPassword);

    void updateRolesUser(String login, Set<RoleDto> roles);

    Pageable<UserDto> getPageableRecords(Pageable<UserDto> pageableDto);
}
