package it.academy.controller.commands.customer;

import it.academy.controller.commands.Command;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteCustomer implements Command {
    private final ICustomerService service = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            service.deleteCustomerById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
