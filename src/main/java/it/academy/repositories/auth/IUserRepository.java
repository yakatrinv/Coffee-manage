package it.academy.repositories.auth;

import it.academy.models.auth.User;
import it.academy.repositories.ICrudRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface IUserRepository extends ICrudRepository<User> {
    User findAuthUser(String login, String pass);

    User findByLogin(String login);
}
