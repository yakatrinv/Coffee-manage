package it.academy.controller.commands.address;

import it.academy.controller.commands.Command;
import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteAddress implements Command {
    private final IAddressService service = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isBlank(id)) {
            service.deleteAddressById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
