package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.services.ICreditCardService;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARD;
import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.DataCreditCard.EDIT_CREDIT_CARD_JSP;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER;
import static it.academy.utils.DataGeneral.MAIN_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class EditCreditCard implements Command {
    private final ICustomerService service = new CustomerService();

    private final ICreditCardService creditCardService = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String prevUrl = request.getParameter(PREV_URL);
        String creditCardId = request.getParameter(ATTR_CREDIT_CARD_ID);

        if (StringUtils.isNotBlank(creditCardId)) {
            CreditCardDto creditCard = creditCardService.findCreditCardById(Integer.parseInt(creditCardId));
            request.setAttribute(ATTR_CREDIT_CARD, creditCard);
        }

        request.setAttribute(PREV_URL, prevUrl);

        CustomerDto loggedCustomer = (CustomerDto) request.getSession().getAttribute(ATTR_LOGGED_CUSTOMER);
        Integer id = loggedCustomer != null ? loggedCustomer.getId() : null;
        if (id != null) {
            CustomerDto customer = service.findCustomerById(id);
            request.setAttribute(ATTR_CUSTOMER, customer);
            return EDIT_CREDIT_CARD_JSP;
        } else {
            return StringUtils.isBlank(prevUrl) ? MAIN_JSP : prevUrl;
        }

    }
}
