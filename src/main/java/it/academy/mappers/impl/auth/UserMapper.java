package it.academy.mappers.impl.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<User, UserDto> {
    Mapper<Role, RoleDto> roleMapper = new RoleMapper();

    @Override
    public User dtoToEntity(UserDto userDTO) {
        Set<Role> roles = userDTO.getRoles() == null ? null :
                userDTO.getRoles()
                        .stream()
                        .map(roleMapper::dtoToEntity)
                        .collect(Collectors.toSet());
        return User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .roles(roles)
                .build();
    }

    @Override
    public UserDto entityToDto(User user) {
        Set<RoleDto> roles = user.getRoles() == null ? null :
                user.getRoles()
                        .stream()
                        .map(roleMapper::entityToDto)
                        .collect(Collectors.toSet());

        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
