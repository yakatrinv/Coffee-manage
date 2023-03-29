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
import java.util.List;

public class RoleService implements IRoleService {
    private final IRoleRepository repository = new RoleRepository();

    private final Mapper<Role, RoleDto> mapper = new RoleMapper();

    private final Mapper<Pageable<Role>, Pageable<RoleDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createRole(RoleDto entityDto) {
        Role role = mapper.dtoToEntity(entityDto);
        role = repository.save(role);
        mapper.entityToDto(role);
    }

    @Override
    public void updateRole(RoleDto entityDto) {
        Role role = mapper.dtoToEntity(entityDto);
        role = repository.update(role);
        mapper.entityToDto(role);
    }

    @Override
    public RoleDto findRoleById(Serializable id) {
        Role role = repository.getById(id);
        return mapper.entityToDto(role);
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
        Pageable<Role> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public List<RoleDto> findAllRoles() {
        List<Role> entities = repository.getAll();
        return entities
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
