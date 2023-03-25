package it.academy.services.auth;

import it.academy.dto.auth.UserAuthDto;
import it.academy.dto.auth.UserDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.auth.UserAuthMapper;
import it.academy.mappers.impl.auth.UserMapper;
import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.repositories.auth.IRoleRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.RoleRepository;
import it.academy.repositories.auth.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;

import static it.academy.utils.Data.ROLE_CUSTOMER;


public class UserService implements  IUserService{
    EncryptService encryptService = new EncryptService();
    private IUserRepository userRepo = new UserRepository();
    private IRoleRepository roleRepository = new RoleRepository();
    private Mapper<User, UserDto> mapper = new UserMapper();
    @Override
    public UserDto createUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User user = mapper.dtoToEntity(userDto);
        //customer
        Role role = roleRepository.findByRoleName(ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        
        byte[] salt = encryptService.generateSalt();
        byte[] encryptedPassword = encryptService
                .getEncryptedPassword(user.getPassword(), salt);
        user.setSalt(encryptService.toHex(salt));
        user.setPassword(encryptService.toHex(encryptedPassword));
        user = userRepo.save(user).orElse(null);
        return mapper.entityToDto(user);
    }
}
