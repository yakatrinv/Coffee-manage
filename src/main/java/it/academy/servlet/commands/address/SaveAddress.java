package it.academy.servlet.commands.address;

import it.academy.converter.IConverter;
import it.academy.converter.impl.AddressConverter;
import it.academy.dto.AddressDto;
import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class SaveAddress implements Command {
    private final IAddressService addressService = new AddressService();
    private final IConverter<AddressDto> converter = new AddressConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AddressDto addressDto = converter.convertToDto(request);
        addressService.createAddress(addressDto);

        return request.getParameter(PREV_URL);
    }
}
