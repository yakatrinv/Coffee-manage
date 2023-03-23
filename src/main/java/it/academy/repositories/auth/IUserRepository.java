package it.academy.repositories.auth;

import it.academy.models.auth.User;
import it.academy.repositories.ICrudRepository;

public interface IUserRepository extends ICrudRepository<User> {
    User findByLoginAndPass(String login, String password);

    User findByLoginAndRole(String login, Integer roleId);
}
