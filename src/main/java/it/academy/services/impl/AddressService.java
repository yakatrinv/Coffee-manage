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
import java.util.List;

public class AddressService implements IAddressService {
    private final IAddressRepository repository
            = new AddressRepository();

    private final Mapper<Address, AddressDto> mapper = new AddressMapper();

    private final Mapper<Pageable<Address>, Pageable<AddressDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createAddress(AddressDto entityDto) {
        Address address = mapper.dtoToEntity(entityDto);
        address = repository.save(address);
        mapper.entityToDto(address);
    }

    @Override
    public void updateAddress(AddressDto entityDto) {
        Address address = mapper.dtoToEntity(entityDto);
        address = repository.update(address);
        mapper.entityToDto(address);
    }

    @Override
    public AddressDto findAddressById(Serializable id) {
        Address address = repository.getById(id);
        return mapper.entityToDto(address);
    }

    @Override
    public void deleteAddressById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public Pageable<AddressDto> getPageableRecords(Pageable<AddressDto> pageableDto) {
        Pageable<Address> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }

    @Override
    public List<AddressDto> findAllAddresses() {
        List<Address> addresses = repository.getAll();
        return addresses
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
