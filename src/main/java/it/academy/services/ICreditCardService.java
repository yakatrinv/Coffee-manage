package it.academy.services;

import it.academy.dto.CreditCardDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ICreditCardService {
    void createCreditCard(CreditCardDto creditCardDto);

    void updateCreditCard(CreditCardDto creditCardDto);

    CreditCardDto findCreditCardById(Serializable id);

    void deleteCreditCardById(Serializable id);

    List<CreditCardDto> findAllCreditCards();

    List<CreditCardDto> getCustomerCreditCards(Serializable id);

    Pageable<CreditCardDto> getPageableRecords(Pageable<CreditCardDto> pageable);
}
