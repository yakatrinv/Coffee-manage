package it.academy.services;

import it.academy.dto.AddressDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IAddressService {
    void createAddress(AddressDto addressDto);

    void updateAddress(AddressDto addressDto);

    AddressDto findAddressById(Serializable id);

    void deleteAddressById(Serializable id);

    List<AddressDto> findAllAddresses();

    List<String> findAllCities();

    Pageable<AddressDto> getPageableRecords(Pageable<AddressDto> pageable);
}
