package it.academy.services.impl;

import it.academy.dto.DiscountDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.DiscountMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.Discount;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IDiscountRepository;
import it.academy.repositories.impl.DiscountRepository;
import it.academy.services.IDiscountService;

import java.io.Serializable;
import java.util.List;

public class DiscountService implements IDiscountService {
    private final Mapper<Discount, DiscountDto> mapper = new DiscountMapper();

    private final Mapper<Pageable<Discount>, Pageable<DiscountDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final IDiscountRepository repository
            = new DiscountRepository();

    @Override
    public DiscountDto createDiscount(DiscountDto entityDto) {
        Discount entity = mapper.dtoToEntity(entityDto);
        entity = repository.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public DiscountDto updateDiscount(DiscountDto entityDto) {
        Discount entity = mapper.dtoToEntity(entityDto);
        entity = repository.update(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public DiscountDto findDiscountById(Serializable id) {
        Discount entity = repository.getById(id);
        return mapper.entityToDto(entity);
    }

    @Override
    public void deleteDiscountById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<DiscountDto> getPageableRecords(Pageable<DiscountDto> pageableDto) {
        Pageable<Discount> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public List<DiscountDto> findAllDiscounts() {
        List<Discount> entities = repository.getAll();
        return entities
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
