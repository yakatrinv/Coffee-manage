package it.academy.mappers.impl.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<User, UserDto> {
    private final Mapper<Role, RoleDto> mapper = new RoleMapper();

    @Override
    public User dtoToEntity(UserDto userDto) {
        Set<Role> roles = userDto.getRoles() == null ? null :
                userDto.getRoles()
                        .stream()
                        .map(mapper::dtoToEntity)
                        .collect(Collectors.toSet());

        return User.builder()
                .login(userDto.getLogin())
                .roles(roles)
                .build();
    }

    @Override
    public UserDto entityToDto(User user) {
        Set<RoleDto> roles = user.getRoles() == null ? null :
                user.getRoles()
                        .stream()
                        .map(mapper::entityToDto)
                        .collect(Collectors.toSet());

        return UserDto.builder()
                .login(user.getLogin())
                .roles(roles)
                .build();
    }
}
