package it.academy.repositories.impl;

import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {
    public static final String NEW_USER_LOGIN = "New customer user";

    public static final String USER_PASSWORD = "123";

    IUserRepository userRepository = new UserRepository();
    ICustomerRepository customerRepository = new CustomerRepository();

    @Test
    void isShouldGetCustomerByLoginUser() {
        User newUser = User.builder()
                .login(NEW_USER_LOGIN)
                .password(USER_PASSWORD)
                .build();

        userRepository.save(newUser);

        Customer customer = Customer.builder()
                .user(newUser)
                .build();

        customerRepository.save(customer);

        Customer findCustomer = customerRepository.getCustomerByLoginUser(NEW_USER_LOGIN);

        assertAll(
                () -> assertNotNull(findCustomer),
                () -> assertEquals(newUser.getId(),findCustomer.getUser().getId()),
                () -> assertEquals(newUser.getLogin(),findCustomer.getUser().getLogin())
        );

        customerRepository.delete(findCustomer.getId());
        userRepository.delete(newUser.getId());
    }
}