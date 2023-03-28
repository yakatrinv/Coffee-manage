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
    private final Mapper<Purchase, PurchaseDto> mapper = new PurchaseMapper();

    private final Mapper<Pageable<Purchase>, Pageable<PurchaseDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final IPurchaseRepository repository
            = new PurchaseRepository();

    @Override
    public PurchaseDto createPurchase(PurchaseDto entityDto) {
        Purchase entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public PurchaseDto updatePurchase(PurchaseDto entityDto) {
        Purchase entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public PurchaseDto findPurchaseById(Serializable id) {
        Purchase entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deletePurchaseById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<PurchaseDto> getPageableRecords(Pageable<PurchaseDto> pageableDto) {
        Pageable<Purchase> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
