package it.academy.services.auth;

import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.auth.UserMapper;
import it.academy.models.auth.User;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;

public class RoleService implements IRoleService {
    @Override
    public UserDto createUser(UserDto userDto) {
//        User entity = mapper.dtoToEntity(userDto);
//        User user = repository.save(entity).orElse(null);
//        return mapper.entityToDto(user);
        return null;
    }
}
