package it.academy.controller.commands.address;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.AddressConverter;
import it.academy.dto.AddressDto;
import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class SaveAddress implements Command {
    private final IAddressService service = new AddressService();

    private final IConverter<AddressDto> converter = new AddressConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AddressDto addressDto = converter.convertToDto(request);
        service.createAddress(addressDto);

        return request.getParameter(PREV_URL);
    }
}
