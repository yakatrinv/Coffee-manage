package it.academy.servlet.commands.customer;

import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteCustomer implements Command {
    private final ICustomerService service = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            service.deleteCustomerById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
