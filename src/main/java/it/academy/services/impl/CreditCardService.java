package it.academy.services.impl;

import it.academy.dto.CreditCardDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CreditCardMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.CreditCard;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ICreditCardRepository;
import it.academy.repositories.impl.CreditCardRepository;
import it.academy.services.ICreditCardService;

import java.io.Serializable;
import java.util.List;

public class CreditCardService implements ICreditCardService {
    private final ICreditCardRepository repository
            = new CreditCardRepository();

    private final Mapper<CreditCard, CreditCardDto> mapper = new CreditCardMapper();

    private final Mapper<Pageable<CreditCard>, Pageable<CreditCardDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = mapper.dtoToEntity(creditCardDto);
        creditCard = repository.save(creditCard);
        mapper.entityToDto(creditCard);
    }

    @Override
    public void updateCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = mapper.dtoToEntity(creditCardDto);
        creditCard = repository.update(creditCard);
        mapper.entityToDto(creditCard);
    }

    @Override
    public CreditCardDto findCreditCardById(Serializable id) {
        CreditCard creditCard = repository.getById(id);
        return mapper.entityToDto(creditCard);
    }

    @Override
    public void deleteCreditCardById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<CreditCardDto> findAllCreditCards() {
        List<CreditCard> creditCards = repository.getAll();
        return creditCards == null ? null : creditCards
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public List<CreditCardDto> getCustomerCreditCards(Serializable id) {
        List<CreditCard> creditCards = repository.getCreditCards(id);
        return creditCards == null ? null : creditCards
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public Pageable<CreditCardDto> getPageableRecords(Pageable<CreditCardDto> pageableDto) {
        Pageable<CreditCard> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
