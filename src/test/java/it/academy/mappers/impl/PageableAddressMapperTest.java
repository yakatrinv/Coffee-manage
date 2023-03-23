package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.Mapper;
import it.academy.models.Address;
import it.academy.models.pageable.Pageable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PageableAddressMapperTest {
    public static final int PAGE_NUMBER = 2;
    public static final int PAGE_SIZE = 5;
    public static final int LAST_PAGE_NUMBER = 4;
    public static final String SORT_FIELD = "city";
    public static final String ATTR_CITY = "city";
    public static final String CITY_VALUE = "Minsk";
    public static final String ATTR_STREET = "street";
    public static final String STREET_VALUE = "Lenina";
    Mapper<Address, AddressDto> addressMapper = new AddressMapper();
    Mapper<Pageable<Address>, Pageable<AddressDto>> mapper =
            new PageableMapper<>(addressMapper);

    @Test
    void isShouldConvertPageableDtoToPageable() {
        HashMap<String,Object> searchFields = new HashMap<>();
        searchFields.put(ATTR_CITY,CITY_VALUE);
        searchFields.put(ATTR_STREET, STREET_VALUE);
        List<AddressDto> addressesDto;

        addressesDto = new ArrayList<>();
        addressesDto.add(AddressDto.builder()
                .city(CITY_VALUE)
                .street(STREET_VALUE)
                .build());

        Pageable<AddressDto> pageableDto = Pageable
                .<AddressDto>builder()
                .pageNumber(PAGE_NUMBER)
                .pageSize(PAGE_SIZE)
                .lastPageNumber(LAST_PAGE_NUMBER)
                .sortField(SORT_FIELD)
                .searchFields(searchFields)
                .records(addressesDto)
                .build();

        Pageable<Address> pageable = mapper.dtoToEntity(pageableDto);

        assertAll(
                () -> assertEquals(PAGE_NUMBER,pageable.getPageNumber()),
                () -> assertEquals(PAGE_SIZE,pageable.getPageSize()),
                () -> assertEquals(LAST_PAGE_NUMBER,pageable.getLastPageNumber()),
                () -> assertEquals(SORT_FIELD,pageable.getSortField()),
                () -> assertEquals(2,pageable.getSearchFields().size()),
                () -> assertEquals(CITY_VALUE,pageable.getSearchFields().get(ATTR_CITY)),
                () -> assertEquals(STREET_VALUE,pageable.getSearchFields().get(ATTR_STREET)),
                () -> assertNotNull(pageable.getRecords()),
                () -> assertEquals(1,pageable.getRecords().size()),
                () -> assertEquals(CITY_VALUE,pageable.getRecords().get(0).getCity()),
                () -> assertEquals(STREET_VALUE,pageable.getRecords().get(0).getStreet())

        );
    }


    @Test
    void isShouldConvertPageableDtoToPageableWithNullRecords() {
        HashMap<String,Object> searchFields = new HashMap<>();
        searchFields.put(ATTR_CITY,CITY_VALUE);
        searchFields.put(ATTR_STREET, STREET_VALUE);
        List<AddressDto> addressesDto = null;

        Pageable<AddressDto> pageableDto = Pageable
                .<AddressDto>builder()
                .pageNumber(PAGE_NUMBER)
                .pageSize(PAGE_SIZE)
                .lastPageNumber(LAST_PAGE_NUMBER)
                .sortField(SORT_FIELD)
                .searchFields(searchFields)
                .records(addressesDto)
                .build();

        Pageable<Address> pageable = mapper.dtoToEntity(pageableDto);

        assertAll(
                () -> assertEquals(PAGE_NUMBER,pageable.getPageNumber()),
                () -> assertEquals(PAGE_SIZE,pageable.getPageSize()),
                () -> assertEquals(LAST_PAGE_NUMBER,pageable.getLastPageNumber()),
                () -> assertEquals(SORT_FIELD,pageable.getSortField()),
                () -> assertEquals(2,pageable.getSearchFields().size()),
                () -> assertEquals(CITY_VALUE,pageable.getSearchFields().get(ATTR_CITY)),
                () -> assertEquals(STREET_VALUE,pageable.getSearchFields().get(ATTR_STREET)),
                () -> assertNull(pageable.getRecords())

        );
    }

    @Test
    void isShouldConvertPageableToPageableDto() {
        HashMap<String,Object> searchFields = new HashMap<>();
        searchFields.put(ATTR_CITY,CITY_VALUE);
        searchFields.put(ATTR_STREET, STREET_VALUE);
        List<Address> addresses;

        addresses = new ArrayList<>();
        addresses.add(Address.builder()
                .city(CITY_VALUE)
                .street(STREET_VALUE)
                .build());

        Pageable<Address> pageable = Pageable
                .<Address>builder()
                .pageNumber(PAGE_NUMBER)
                .pageSize(PAGE_SIZE)
                .lastPageNumber(LAST_PAGE_NUMBER)
                .sortField(SORT_FIELD)
                .searchFields(searchFields)
                .records(addresses)
                .build();

        Pageable<AddressDto> pageableDto = mapper.entityToDto(pageable);

        assertAll(
                () -> assertEquals(PAGE_NUMBER,pageableDto.getPageNumber()),
                () -> assertEquals(PAGE_SIZE,pageableDto.getPageSize()),
                () -> assertEquals(LAST_PAGE_NUMBER,pageableDto.getLastPageNumber()),
                () -> assertEquals(SORT_FIELD,pageableDto.getSortField()),
                () -> assertEquals(2,pageableDto.getSearchFields().size()),
                () -> assertEquals(CITY_VALUE,pageableDto.getSearchFields().get(ATTR_CITY)),
                () -> assertEquals(STREET_VALUE,pageableDto.getSearchFields().get(ATTR_STREET)),
                () -> assertNotNull(pageableDto.getRecords()),
                () -> assertEquals(1,pageableDto.getRecords().size()),
                () -> assertEquals(CITY_VALUE,pageableDto.getRecords().get(0).getCity()),
                () -> assertEquals(STREET_VALUE,pageableDto.getRecords().get(0).getStreet())

        );
    }

    @Test
    void isShouldConvertPageableToPageableDtoWithNullRecords() {
        HashMap<String,Object> searchFields = new HashMap<>();
        searchFields.put(ATTR_CITY,CITY_VALUE);
        searchFields.put(ATTR_STREET, STREET_VALUE);
        List<Address> addresses = null;

        Pageable<Address> pageable = Pageable
                .<Address>builder()
                .pageNumber(PAGE_NUMBER)
                .pageSize(PAGE_SIZE)
                .lastPageNumber(LAST_PAGE_NUMBER)
                .sortField(SORT_FIELD)
                .searchFields(searchFields)
                .records(addresses)
                .build();

        Pageable<AddressDto> pageableDto = mapper.entityToDto(pageable);

        assertAll(
                () -> assertEquals(PAGE_NUMBER,pageableDto.getPageNumber()),
                () -> assertEquals(PAGE_SIZE,pageableDto.getPageSize()),
                () -> assertEquals(LAST_PAGE_NUMBER,pageableDto.getLastPageNumber()),
                () -> assertEquals(SORT_FIELD,pageableDto.getSortField()),
                () -> assertEquals(2,pageableDto.getSearchFields().size()),
                () -> assertEquals(CITY_VALUE,pageableDto.getSearchFields().get(ATTR_CITY)),
                () -> assertEquals(STREET_VALUE,pageableDto.getSearchFields().get(ATTR_STREET)),
                () -> assertNull(pageableDto.getRecords())

        );
    }
}