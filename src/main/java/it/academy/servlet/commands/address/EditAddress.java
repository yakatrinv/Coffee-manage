package it.academy.servlet.commands.address;

import it.academy.dto.AddressDto;
import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ADDRESS;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.EDIT_ADDRESS_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditAddress implements Command {
    private final IAddressService service = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AddressDto address;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            address = service.findAddressById(Integer.valueOf(id));
            request.setAttribute(ATTR_ADDRESS, address);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_ADDRESS_JSP;
    }
}
