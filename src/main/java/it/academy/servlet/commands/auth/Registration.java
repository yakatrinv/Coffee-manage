package it.academy.servlet.commands.auth;

import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_LOGIN;
import static it.academy.utils.Data.ATTR_PASSWORD;
import static it.academy.utils.Data.LOGIN_JSP;

public class Registration implements Command {
    private final ICustomerService customerService = new CustomerService();

    private final IConverter<CustomerDto> converter = new CustomerConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);

        CustomerDto customerDto = converter.convertToDto(request);
        customerService.createCustomer(login, password, customerDto);

        return LOGIN_JSP;
    }
}
