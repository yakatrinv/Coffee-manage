package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.IAddressMapper;
import it.academy.models.Address;

import javax.servlet.http.HttpServletRequest;

public class AddressMapper implements IAddressMapper {
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

    @Override
    public AddressDto requestToDto(HttpServletRequest request) {
        String id = request.getParameter("id");
        String city = request.getParameter("city");
        String street = request.getParameter("street");

        return AddressDto.builder()
                .id(isEmpty(id) ? null : Integer.valueOf(id))
                .city(city)
                .street(street)
                .build();
    }

    private static boolean isEmpty(String value) {
        return (value == null || value.isEmpty());
    }
}
