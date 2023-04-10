package it.academy.repositories.impl;

import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.auth.User;
import it.academy.repositories.ICreditCardRepository;
import it.academy.repositories.ICustomerRepository;
import it.academy.repositories.auth.IUserRepository;
import it.academy.repositories.auth.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditCardRepositoryTest {
    public static final String NEW_USER_LOGIN = "New user";
    public static final String PASSWORD = "123";
    public static final String CREDIT_CARD_NUMBER = "1234";
    public static final int FIRSR_INDEX = 0;
    public static final int COUNT_CREDIT_CARDS = 1;
    public static final String ERROR_ID ="1d";
    IUserRepository userRepository = new UserRepository();
    ICustomerRepository customerRepository = new CustomerRepository();
    ICreditCardRepository creditCardRepository = new CreditCardRepository();

    @Test
    void isShouldGetCreditCardsCustomer() {
        User user = User.builder()
                .login(NEW_USER_LOGIN)
                .password(PASSWORD)
                .build();

        userRepository.save(user);

        Customer customer = Customer.builder()
                .user(user)
                .build();

        Customer newCustomer = customerRepository.save(customer);

        CreditCard newCreditCard = CreditCard.builder()
                .number(CREDIT_CARD_NUMBER)
                .customer(newCustomer)
                .build();

        creditCardRepository.save(newCreditCard);

        List<CreditCard> creditCards = creditCardRepository.getCreditCards(newCustomer.getId());

        assertAll(
                () -> assertEquals(COUNT_CREDIT_CARDS, creditCards.size()),
                () -> assertEquals(CREDIT_CARD_NUMBER, creditCards.get(FIRSR_INDEX).getNumber()),
                () -> assertEquals(customer.getId(), creditCards.get(FIRSR_INDEX).getCustomer().getId())
        );

        creditCards.forEach(creditCard -> creditCardRepository.delete(creditCard.getId()));
        customerRepository.delete(newCustomer.getId());
        userRepository.delete(user.getId());
    }

    @Test
    void isShouldGetNullCreditCardThrow() {
        creditCardRepository.getCreditCards(ERROR_ID);
    }
}