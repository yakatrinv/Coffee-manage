package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IRoleService {
    RoleDto createRole(RoleDto entityDto);

    RoleDto updateRole(RoleDto entityDto);

    RoleDto findRoleById(Serializable id);

    void deleteAddressById(Serializable id);

    RoleDto findByRoleName(String roleName);

    Pageable<RoleDto> getPageableRecords(Pageable<RoleDto> pageableDto);
}
