package it.academy.mappers.impl.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.mappers.Mapper;
import it.academy.models.auth.Role;

public class RoleMapper implements Mapper<Role, RoleDto> {
    @Override
    public Role dtoToEntity(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.getId())
                .roleName(roleDto.getRoleName())
                .build();
    }

    @Override
    public RoleDto entityToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
