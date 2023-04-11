package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ATTR_LOGIN;
import static it.academy.utils.DataAuth.ATTR_PASSWORD;
import static it.academy.utils.DataAuth.LOGIN_JSP;
import static it.academy.utils.DataGeneral.ATTR_MESSAGE;
import static it.academy.utils.DataGeneral.ERROR_JSP;
import static it.academy.utils.DataGeneral.ERROR_REG;

public class Registration implements Command {
    private final ICustomerService service = new CustomerService();

    private final IConverter<CustomerDto> converter = new CustomerConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);

        CustomerDto customer = converter.convertToDto(request);
        service.createCustomer(login, password, customer);

        if (customer == null) {
            request.setAttribute(ATTR_MESSAGE, ERROR_REG);
            return ERROR_JSP;
        } else {
            return LOGIN_JSP;
        }
    }
}
