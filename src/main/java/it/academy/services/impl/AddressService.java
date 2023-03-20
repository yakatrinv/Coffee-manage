package it.academy.services.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.IAddressMapper;
import it.academy.mappers.impl.AddressMapper;
import it.academy.models.Address;
import it.academy.repositories.IAddressRepository;
import it.academy.repositories.impl.AddressRepository;
import it.academy.services.IAddressService;
import it.academy.services.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

public class AddressService implements IAddressService {
    private final IAddressMapper addressMapper = new AddressMapper();

    private final IAddressRepository addressRepository = new AddressRepository();

    @Override
    public AddressDto createAddress(AddressDto addressDto) {
        Address entity = addressMapper.dtoToEntity(addressDto);
        Address address = addressRepository.save(entity).orElse(null);
        return addressMapper.entityToDto(address);
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        Address entity = addressMapper.dtoToEntity(addressDto);
        Address customer = addressRepository.update(entity).orElse(null);
        return addressMapper.entityToDto(customer);
    }

    @Override
    public AddressDto findAddressById(Serializable id) {
        Address customer = addressRepository.getById(id).orElse(null);
        return addressMapper.entityToDto(customer);
    }

    @Override
    public List<AddressDto> findAllAddresses() {
        List<Address> customers = addressRepository.getAll();
        return customers
                .stream()
                .map(addressMapper::entityToDto)
                .toList();
    }

    @Override
    public void deleteAddressById(Serializable id) {
        addressRepository.delete(id);
    }

    @Override
    public Pageable<AddressDto> getDataPage(HttpServletRequest request) {
        Pageable<Address> pageable = addressMapper.getPageable(request);
        addressRepository.fillPageable(pageable);
        return addressMapper.pageableToDto(pageable);
    }
}
