package it.academy.services;

import it.academy.dto.AddressDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IAddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressDto updateAddress(AddressDto addressDto);

    AddressDto findAddressById(Serializable id);

    void deleteAddressById(Serializable id);

    Pageable<AddressDto> getPageableRecords(Pageable<AddressDto> pageable);
}
