package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.auth.RoleMapper;
import it.academy.mappers.impl.auth.UserMapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class UserService implements IUserService {
    private final IUserRepository repository = new UserRepository();

    private final Mapper<User, UserDto> userMapper = new UserMapper();

    private final Mapper<Role, RoleDto> roleMapper = new RoleMapper();

    private final Mapper<Pageable<User>, Pageable<UserDto>> mapperP =
            new PageableMapper<>(userMapper);

    @Override
    public UserDto findAuthUser(String login, String password) {
        User user = repository.findAuthUser(login, password);
        return user == null ? null : userMapper.entityToDto(user);
    }

    @Override
    public UserDto findUserByLogin(String login) {
        User user = repository.findUserByLogin(login);
        return user == null ? null : userMapper.entityToDto(user);
    }

    @Override
    public User createUser(String login, String password, Set<RoleDto> roles) {
        User findUser = repository.findUserByLogin(login);

        if (findUser == null) {
            findUser = User.builder()
                    .login(login)
                    .password(password)
                    .roles(roles
                            .stream()
                            .map(roleMapper::dtoToEntity)
                            .collect(Collectors.toSet()))
                    .build();

            repository.save(findUser);
        }
        return findUser;
    }

    @Override
    public void updatePassUser(String login, String password, String oldPassword) {
        User user = repository.findAuthUser(login, oldPassword);

        if (user != null) {
            repository.updatePassUser(user, password);
        }
    }

    @Override
    public void updateRolesUser(String login, Set<RoleDto> roles) {
        User user = repository.findUserByLogin(login);

        if (user != null) {
            user.setRoles(
                    roles.stream()
                            .map(roleMapper::dtoToEntity)
                            .collect(Collectors.toSet())
            );
            repository.update(user);
        }
    }

    @Override
    public Pageable<UserDto> getPageableRecords(Pageable<UserDto> pageableDto) {
        Pageable<User> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public void deleteUserByLogin(String login) {
        User user = repository.findUserByLogin(login);
        if (user != null) {
            repository.delete(user.getId());
        }
    }
}
