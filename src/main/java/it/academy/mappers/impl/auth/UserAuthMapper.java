package it.academy.mappers.impl.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserAuthDto;
import it.academy.mappers.Mapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserAuthMapper implements Mapper<User, UserAuthDto> {
    Mapper<Role, RoleDto> roleMapper = new RoleMapper();

    @Override
    public User dtoToEntity(UserAuthDto userAuthDto) {
        Set<Role> roles = userAuthDto.getRoles() == null ? null :
                userAuthDto.getRoles()
                        .stream()
                        .map(roleMapper::dtoToEntity)
                        .collect(Collectors.toSet());

        return User.builder()
                .login(userAuthDto.getLogin())
                .roles(roles)
                .build();
    }

    @Override
    public UserAuthDto entityToDto(User user) {
        Set<RoleDto> roles = user.getRoles() == null ? null :
                user.getRoles()
                        .stream()
                        .map(roleMapper::entityToDto)
                        .collect(Collectors.toSet());

        return UserAuthDto.builder()
                .login(user.getLogin())
                .roles(roles)
                .build();
    }
}
