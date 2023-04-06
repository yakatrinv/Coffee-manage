package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.dto.CreditCardDto;
import it.academy.services.ICreditCardService;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.CustomerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataCreditCard.ATTR_NUMBER;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class SaveCreditCard implements Command {
    private final ICreditCardService service = new CreditCardService();
    private final ICustomerService customerService = new CustomerService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String customerIdS = request.getParameter(ATTR_CUSTOMER_ID);
        String number = request.getParameter(ATTR_NUMBER);

        Integer customerId = (StringUtils.isBlank(customerIdS)) ?
                null : Integer.parseInt(customerIdS);

        CreditCardDto creditCardDto = CreditCardDto.builder()
                .customer(customerService.findCustomerById(customerId))
                .number(number)
                .build();
        if (customerId != null) {
            service.createCreditCard(creditCardDto);
        }

        return request.getParameter(PREV_URL);
    }
}
