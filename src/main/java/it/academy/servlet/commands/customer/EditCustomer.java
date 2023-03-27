package it.academy.servlet.commands.customer;

import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_CUSTOMER;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.EDIT_CUSTOMER_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditCustomer implements Command {
    private final ICustomerService customerService = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDto customer;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            customer = customerService.findCustomerById(Integer.valueOf(id));
            request.setAttribute(ATTR_CUSTOMER, customer);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_CUSTOMER_JSP;
    }
}
