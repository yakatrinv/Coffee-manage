package it.academy.servlet.commands.customer;

import it.academy.converter.IConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.dto.CustomerDto;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class UpdateCustomer implements Command {
    private final ICustomerService customerService = new CustomerService();

    private final IConverter<CustomerDto> converter = new CustomerConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDto customerDto = converter.convertToDto(request);
        customerService.updateCustomer(customerDto);

        return request.getParameter(PREV_URL);
    }
}
