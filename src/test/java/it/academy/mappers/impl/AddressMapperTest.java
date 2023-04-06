package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.Mapper;
import it.academy.models.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressMapperTest {
    public static final String CITY = "Minsk";
    public static final String STREET = "Lenina";
    Mapper<Address, AddressDto> mapper = new AddressMapper();

    @Test
    void isShouldConvertAddressDtoToAddress() {
        Address address = Address.builder()
                .city(CITY)
                .street(STREET)
                .build();
        AddressDto addressDto = mapper.entityToDto(address);

        assertAll(
                () -> assertEquals(CITY, addressDto.getCity()),
                () -> assertEquals(STREET, addressDto.getStreet())

        );
    }

    @Test
    void isShouldConvertAddressToAddressDto() {
        AddressDto addressDto = AddressDto.builder()
                .city(CITY)
                .street(STREET)
                .build();
        Address address = mapper.dtoToEntity(addressDto);

        assertAll(
                () -> assertEquals(CITY, address.getCity()),
                () -> assertEquals(STREET, address.getStreet())

        );
    }
}