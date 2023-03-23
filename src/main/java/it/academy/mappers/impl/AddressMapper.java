package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.Mapper;
import it.academy.models.Address;

public class AddressMapper implements Mapper<Address, AddressDto> {
    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
    }

    @Override
    public AddressDto entityToDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }
}
