package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IRoleService {
    void createRole(RoleDto entityDto);

    void updateRole(RoleDto entityDto);

    RoleDto findRoleById(Serializable id);

    void deleteRoleById(Serializable id);

    RoleDto findRoleByName(String roleName);

    List<RoleDto> findAllRoles();

    Pageable<RoleDto> getPageableRecords(Pageable<RoleDto> pageableDto);
}
