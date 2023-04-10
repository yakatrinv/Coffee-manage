package it.academy.repositories.auth;

import it.academy.models.auth.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    public static final String NEW_USER_LOGIN = "New user";
    public static final String PASSWORD = "123";
    public static final String NEW_PASSWORD = "321";
    IUserRepository userRepository =new UserRepository();

    @Test
    void isShouldFindAuthUser() {
        User newUser = User.builder()
                .login(NEW_USER_LOGIN)
                .password(PASSWORD)
                .build();

        userRepository.save(newUser);
        User findUser = userRepository.findAuthUser(NEW_USER_LOGIN, PASSWORD);

        assertAll(
                () -> assertNotNull(findUser),
                () -> assertEquals(NEW_USER_LOGIN,findUser.getLogin())
        );

        userRepository.delete(findUser.getId());
    }

    @Test
    void isShouldFindUserByLogin() {
        User newUser = User.builder()
                .login(NEW_USER_LOGIN)
                .password(PASSWORD)
                .build();

        userRepository.save(newUser);
        User findUser = userRepository.findUserByLogin(NEW_USER_LOGIN);

        assertAll(
                () -> assertNotNull(findUser),
                () -> assertEquals(NEW_USER_LOGIN,findUser.getLogin())
        );

        userRepository.delete(findUser.getId());
    }

    @Test
    void save() {
        User newUser = User.builder()
                .login(NEW_USER_LOGIN)
                .password(PASSWORD)
                .build();

        userRepository.save(newUser);

        User findUser = userRepository.findAuthUser(NEW_USER_LOGIN, PASSWORD);

        assertAll(
                () -> assertNotNull(findUser),
                () -> assertEquals(NEW_USER_LOGIN,findUser.getLogin())
        );

        userRepository.delete(findUser.getId());
    }

    @Test
    void updatePassUser() {
        User newUser = User.builder()
                .login(NEW_USER_LOGIN)
                .password(PASSWORD)
                .build();

        userRepository.save(newUser);

        User updUser = userRepository.findAuthUser(NEW_USER_LOGIN, PASSWORD);
        userRepository.updatePassUser(updUser,NEW_PASSWORD);

        User findUser = userRepository.findAuthUser(NEW_USER_LOGIN,NEW_PASSWORD);

        assertNotNull(findUser);

        userRepository.delete(findUser.getId());
    }
}