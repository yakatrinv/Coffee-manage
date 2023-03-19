package it.academy.services;

import it.academy.dto.AddressDto;

import java.io.Serializable;
import java.util.List;

public interface IAddressService {
    AddressDto createAddress(AddressDto addressDto);

    AddressDto updateAddress(AddressDto addressDto);

    AddressDto findAddressById(Serializable id);

    List<AddressDto> findAllAddresses();

    void deleteAddressById(Serializable id);
}
