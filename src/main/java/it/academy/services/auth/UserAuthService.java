package it.academy.services.auth;

import it.academy.dto.auth.RoleDto;
import it.academy.dto.auth.UserAuthDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.auth.UserAuthMapper;
import it.academy.mappers.impl.auth.UserMapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

public class UserAuthService implements IUserAuthService {
    private IUserRepository userRepo = new UserRepository();
    private Mapper<User, UserAuthDto> mapper = new UserAuthMapper();

    public UserAuthDto findAuthUser(String login, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = userRepo.findAuthUser(login, password);

        if (user == null) {
            return null;
        }

        return mapper.entityToDto(user);
    }


}
