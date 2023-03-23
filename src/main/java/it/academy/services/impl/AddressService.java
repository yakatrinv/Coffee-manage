package it.academy.services.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.AddressMapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.models.Address;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.IAddressRepository;
import it.academy.repositories.impl.AddressRepository;
import it.academy.services.IAddressService;

import java.io.Serializable;

public class AddressService implements IAddressService {
    private final Mapper<Address, AddressDto> mapper = new AddressMapper();
    private final Mapper<Pageable<Address>, Pageable<AddressDto>> pageMapper =
            new PageableMapper<>(mapper);

    private final IAddressRepository repository
            = new AddressRepository();

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address entity = mapper.dtoToEntity(addressDto);
        Address address = repository.save(entity).orElse(null);
        return mapper.entityToDto(address);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        Address entity = mapper.dtoToEntity(addressDto);
        Address customer = repository.update(entity).orElse(null);
        return mapper.entityToDto(customer);
    }

    @Override
    public AddressDto findAddressById(Serializable id) {
        Address customer = repository.getById(id).orElse(null);
        return mapper.entityToDto(customer);
    }

    @Override
    public void deleteAddressById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<AddressDto> getPageableRecords(Pageable<AddressDto> pageableDto) {
        Pageable<Address> pageable = pageMapper.dtoToEntity(pageableDto);
        return pageMapper.entityToDto(repository.getPageableRecords(pageable));
    }
}
