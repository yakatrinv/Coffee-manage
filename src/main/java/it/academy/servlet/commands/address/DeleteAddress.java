package it.academy.servlet.commands.address;

import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteAddress implements Command {
    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            addressService.deleteAddressById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
