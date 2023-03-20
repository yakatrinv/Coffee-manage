package it.academy.servlet.commands.address;

import it.academy.dto.AddressDto;
import it.academy.services.IAddressService;
import it.academy.services.Pageable;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static it.academy.utils.DataUI.ADDRESSES_JSP;
import static it.academy.utils.DataUI.ATTR_CITY;
import static it.academy.utils.DataUI.ATTR_STREET;
import static it.academy.utils.DataUI.PAGEABLE;


public class ListAddresses implements Command {
    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<AddressDto> pageable = addressService.getDataPage(request);
        request.setAttribute(PAGEABLE, pageable);

        HashMap<String, Object> filteredFields = pageable.getFilteredFields();
        request.setAttribute(ATTR_CITY, filteredFields.get(ATTR_CITY));
        request.setAttribute(ATTR_STREET, filteredFields.get(ATTR_STREET));

        return ADDRESSES_JSP;
    }
}
