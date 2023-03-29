package it.academy.controller.commands.customer;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.ICustomerConverter;
import it.academy.converter.impl.CustomerConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.CustomerDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.ICustomerService;
import it.academy.services.impl.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_EMAIL;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME;
import static it.academy.utils.Data.ATTR_PHONE;
import static it.academy.utils.Data.ATTR_SEARCH_EMAIL;
import static it.academy.utils.Data.ATTR_SEARCH_NAME;
import static it.academy.utils.Data.ATTR_SEARCH_PHONE;
import static it.academy.utils.Data.ATTR_SEARCH_SURNAME;
import static it.academy.utils.Data.ATTR_SURNAME;
import static it.academy.utils.Data.CUSTOMERS_JSP;
import static it.academy.utils.Data.PAGEABLE;


public class ListCustomers implements Command {
    private final IConverter<Pageable<CustomerDto>> converterP = new PageableConverter<>();

    private final ICustomerService service = new CustomerService();

    private final ICustomerConverter converter = new CustomerConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<CustomerDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_NAME)) {
            request.setAttribute(ATTR_SEARCH_NAME,
                    pageableDto.getSearchFields().get(ATTR_NAME));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_SURNAME)) {
            request.setAttribute(ATTR_SEARCH_SURNAME,
                    pageableDto.getSearchFields().get(ATTR_SURNAME));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_PHONE)) {
            request.setAttribute(ATTR_SEARCH_PHONE,
                    pageableDto.getSearchFields().get(ATTR_PHONE));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_EMAIL)) {
            request.setAttribute(ATTR_SEARCH_EMAIL,
                    pageableDto.getSearchFields().get(ATTR_EMAIL));
        }

        return CUSTOMERS_JSP;
    }
}
