package it.academy.services.auth;


import it.academy.dto.auth.UserAuthDto;
import it.academy.dto.auth.UserDto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserService {
    UserDto createUser(UserDto userDto) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
