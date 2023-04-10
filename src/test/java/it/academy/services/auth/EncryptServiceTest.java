package it.academy.services.auth;

import it.academy.models.auth.User;
import it.academy.repositories.ICrudRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;
import it.academy.repositories.impl.CrudRepository;
import org.junit.jupiter.api.Test;

import static it.academy.utils.DataGeneral.USER_CLASS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncryptServiceTest {

    public static final String NEW_USER = "New user";
    public static final String PASSWORD = "123";

    public static final String ERROR_PASSWORD = "12";

    @Test
    public void isShouldAddUser() {
        EncryptService service = new EncryptService();
        byte[] salt = service.generateSalt();
        byte[] encryptedPassword = service.getEncryptedPassword(PASSWORD, salt);
        User user = User.builder()
                .login(NEW_USER)
                .salt(service.toHex(salt))
                .password(service.toHex(encryptedPassword))
                .build();

        ICrudRepository<User> dao = new CrudRepository<>(USER_CLASS);
        dao.save(user);

        User newUser = dao.getById(user.getId());

        byte[] salt1 = service.fromHex(newUser.getSalt());
        byte[] password = service.fromHex(newUser.getPassword());

        assertAll(
                () -> assertNotNull(newUser),
                () -> assertTrue(service.verifyPassword(PASSWORD, password, salt1)),
                () -> assertFalse(service.verifyPassword(ERROR_PASSWORD, password, salt1))
        );

        dao.delete(newUser.getId());
    }

    @Test
    public void isShouldAddUserWithEncryptedPassword() {
        EncryptService service = new EncryptService();
        User user = User.builder()
                .login(NEW_USER)
                .password(PASSWORD)
                .build();

        IUserRepository dao = new UserRepository();
        dao.save(user);

        User newUser = dao.getById(user.getId());

        byte[] salt = service.fromHex(newUser.getSalt());
        byte[] password = service.fromHex(newUser.getPassword());

        assertAll(
                () -> assertNotNull(newUser),
                () -> assertNotNull(newUser.getSalt()),
                () -> assertNotNull(newUser.getPassword()),
                () -> assertTrue(service.verifyPassword(PASSWORD, password, salt)),
                () -> assertFalse(service.verifyPassword(ERROR_PASSWORD, password, salt))
        );

        dao.delete(newUser.getId());
    }
}