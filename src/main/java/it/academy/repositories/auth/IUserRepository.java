package it.academy.repositories.auth;

import it.academy.models.auth.Role;
import it.academy.models.auth.User;
import it.academy.repositories.ICrudRepository;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

public interface IUserRepository extends ICrudRepository<User> {
    User findByLogin(String login);

    User findAuthUser(String login, String pass) throws NoSuchAlgorithmException, InvalidKeySpecException;

//    Set<Role> getUserRoles(EntityManager entityManager1, User user);

//    User findByLoginAndPass(String login, String password);
//
//    User findByLoginWithRoles(String login);
//
//    User findByLoginAndRole(String login, Integer roleId);
}
