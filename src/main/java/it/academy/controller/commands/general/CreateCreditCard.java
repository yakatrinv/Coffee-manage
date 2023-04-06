package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.DataCreditCard.ADD_CREDIT_CARD_JSP;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataGeneral.MAIN_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class CreateCreditCard implements Command {
    private final ICustomerService service = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        request.setAttribute(PREV_URL, prevUrl);

        CustomerDto loggedCustomer = (CustomerDto) request.getSession().getAttribute(ATTR_LOGGED_CUSTOMER);
        Integer id = loggedCustomer != null ? loggedCustomer.getId() : null;
        if (id != null) {
            CustomerDto customer = service.findCustomerById(id);
            request.setAttribute(ATTR_CUSTOMER, customer);
            return ADD_CREDIT_CARD_JSP;
        } else {
            return StringUtils.isBlank(prevUrl) ? MAIN_JSP : prevUrl;
        }

    }
}
