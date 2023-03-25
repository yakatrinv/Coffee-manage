package it.academy.services.auth;

import it.academy.dto.auth.UserAuthDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.auth.UserAuthMapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.repositories.auth.IRoleRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.RoleRepository;
import it.academy.repositories.auth.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static it.academy.utils.Data.ROLE_CUSTOMER;

public class UserAuthService implements IUserAuthService {
    private final IUserRepository userRepo = new UserRepository();

    private final IRoleRepository roleRepository = new RoleRepository();

    private final Mapper<User, UserAuthDto> mapper = new UserAuthMapper();

    public UserAuthDto findAuthUser(String login, String password) {
        User user = userRepo.findAuthUser(login, password);
        return user == null ? null : mapper.entityToDto(user);
    }

    @Override
    public void createUser(String login, String password) {
        //customer
        Role role = roleRepository.findByRoleName(ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User findUser = userRepo.findByLogin(login);

        if (findUser == null) {
            findUser = User.builder()
                    .login(login)
                    .password(password)
                    .roles(roles)
                    .build();

            userRepo.save(findUser);
        } else if (findUser.getRoles() == null || findUser.getRoles().isEmpty()) {
            findUser.setRoles(roles);
            userRepo.update(findUser);
        } else if (!findUser.getRoles().contains(role)) {
            Set<Role> userRoles = findUser.getRoles();
            userRoles.add(role);
            findUser.setRoles(roles);
            userRepo.update(findUser);
        }
    }
}
