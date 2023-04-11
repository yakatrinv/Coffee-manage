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
    private final IDiscountRepository repository
            = new DiscountRepository();

    private final Mapper<Discount, DiscountDto> mapper = new DiscountMapper();

    private final Mapper<Pageable<Discount>, Pageable<DiscountDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createDiscount(DiscountDto discountDto) {
        Discount discount = mapper.dtoToEntity(discountDto);
        discount = repository.save(discount);
        mapper.entityToDto(discount);
    }

    @Override
    public void updateDiscount(DiscountDto discountDto) {
        Discount discount = mapper.dtoToEntity(discountDto);
        discount = repository.update(discount);
        mapper.entityToDto(discount);
    }

    @Override
    public DiscountDto findDiscountById(Serializable id) {
        Discount discount = repository.getById(id);
        return mapper.entityToDto(discount);
    }

    @Override
    public void deleteDiscountById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<DiscountDto> findAllDiscounts() {
        List<Discount> discounts = repository.getAll();
        return discounts
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public Discount getPercentDiscount(float sum) {
        return repository.getPercentDiscount(sum);
    }

    @Override
    public Pageable<DiscountDto> getPageableRecords(Pageable<DiscountDto> pageableDto) {
        Pageable<Discount> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
