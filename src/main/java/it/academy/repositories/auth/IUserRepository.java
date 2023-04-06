package it.academy.repositories.auth;

import it.academy.models.auth.User;
import it.academy.repositories.ICrudRepository;

public interface IUserRepository extends ICrudRepository<User> {
    User findAuthUser(String login, String pass);

    User findUserByLogin(String login);

    void updatePassUser(User user, String newPassword);
}
