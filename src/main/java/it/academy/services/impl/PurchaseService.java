package it.academy.services.impl;

import it.academy.dto.PurchaseDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.PurchaseMapper;
import it.academy.models.Purchase;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IPurchaseRepository;
import it.academy.repositories.impl.PurchaseRepository;
import it.academy.services.IPurchaseService;

import java.io.Serializable;

public class PurchaseService implements IPurchaseService {
    private final IPurchaseRepository repository
            = new PurchaseRepository();

    private final Mapper<Purchase, PurchaseDto> mapper = new PurchaseMapper();

    private final Mapper<Pageable<Purchase>, Pageable<PurchaseDto>> mapperP =
            new PageableMapper<>(mapper);


    @Override
    public void createPurchase(PurchaseDto entityDto) {
        Purchase purchase = mapper.dtoToEntity(entityDto);
        purchase = repository.save(purchase);
        mapper.entityToDto(purchase);
    }

    @Override
    public void updatePurchase(PurchaseDto entityDto) {
        Purchase purchase = mapper.dtoToEntity(entityDto);
        purchase = repository.update(purchase);
        mapper.entityToDto(purchase);
    }

    @Override
    public PurchaseDto findPurchaseById(Serializable id) {
        Purchase purchase = repository.getById(id);
        return mapper.entityToDto(purchase);
    }

    @Override
    public void deletePurchaseById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<PurchaseDto> getPageableRecords(Pageable<PurchaseDto> pageableDto) {
        Pageable<Purchase> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
