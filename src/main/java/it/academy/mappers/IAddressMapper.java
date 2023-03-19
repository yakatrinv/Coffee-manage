package it.academy.mappers;

import it.academy.dto.AddressDto;
import it.academy.models.Address;

import javax.servlet.http.HttpServletRequest;

public interface IAddressMapper extends Mapper<Address, AddressDto> {
    AddressDto requestToDto(HttpServletRequest request);
}
