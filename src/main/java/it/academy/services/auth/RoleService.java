package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.auth.RoleMapper;
import it.academy.models.auth.Role;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.auth.IRoleRepository;
import it.academy.repositories.auth.RoleRepository;

import java.io.Serializable;

public class RoleService implements IRoleService {
    private final IRoleRepository repository = new RoleRepository();

    private final Mapper<Role, RoleDto> mapper = new RoleMapper();

    private final Mapper<Pageable<Role>, Pageable<RoleDto>> pageMapper =
            new PageableMapper<>(mapper);

    @Override
    public RoleDto createRole(RoleDto entityDto) {
        Role entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public RoleDto updateRole(RoleDto entityDto) {
        Role entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public RoleDto findRoleById(Serializable id) {
        Role entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteAddressById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public RoleDto findByRoleName(String roleName) {
        Role role = repository.findByRoleName(roleName);
        return role == null ? null : mapper.entityToDto(role);
    }

    @Override
    public Pageable<RoleDto> getPageableRecords(Pageable<RoleDto> pageableDto) {
        Pageable<Role> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
