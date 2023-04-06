package it.academy.controller.commands.customer;

import it.academy.controller.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataCustomer.EDIT_CUSTOMER_JSP;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditCustomer implements Command {
    private final ICustomerService service = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDto customer;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            customer = service.findCustomerById(Integer.parseInt(id));
            request.setAttribute(ATTR_CUSTOMER, customer);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_CUSTOMER_JSP;
    }
}
