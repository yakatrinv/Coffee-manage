package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.services.ICreditCardService;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.DataAuth.EDIT_PROFILE_JSP;
import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARDS;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditProfileCustomer implements Command {
    private final ICustomerService service = new CustomerService();

    private final ICreditCardService creditCardService = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDto customer = null;
        CustomerDto loggedCustomer = (CustomerDto) request.getSession().getAttribute(ATTR_LOGGED_CUSTOMER);
        Integer id = loggedCustomer != null ? loggedCustomer.getId() : null;
        if (id != null) {
            customer = service.findCustomerById(id);
            request.setAttribute(ATTR_CUSTOMER, customer);
        }

        if (customer != null) {
            request.setAttribute(ATTR_CREDIT_CARDS, creditCardService.getCustomerCreditCards(id));
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PROFILE_JSP;
    }
}
