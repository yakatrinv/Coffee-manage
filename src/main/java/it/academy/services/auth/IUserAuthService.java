package it.academy.services.auth;

import it.academy.dto.auth.UserAuthDto;
import it.academy.models.auth.User;
import it.academy.models.pageable.Pageable;

public interface IUserAuthService {
    UserAuthDto findAuthUser(String login, String password);

    UserAuthDto finfByLogin(String login);

    User createCustomerUser(String login, String password);

    void createUser(String login, String password, String roles);

    void updatePassUser(String login, String password, String oldPassword);

    Pageable<UserAuthDto> getPageableRecords(Pageable<UserAuthDto> pageableDto);
}
