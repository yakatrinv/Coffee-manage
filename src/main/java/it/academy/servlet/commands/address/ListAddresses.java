package it.academy.servlet.commands.address;

import it.academy.converter.IAddressConverter;
import it.academy.converter.IConverter;
import it.academy.converter.impl.AddressConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.AddressDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADDRESSES_JSP;
import static it.academy.utils.Data.ATTR_CITY;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_SEARCH_CITY;
import static it.academy.utils.Data.ATTR_SEARCH_STREET;
import static it.academy.utils.Data.ATTR_STREET;
import static it.academy.utils.Data.PAGEABLE;


public class ListAddresses implements Command {
    private final IConverter<Pageable<AddressDto>> converter = new PageableConverter<>();

    private final IAddressConverter addressConverter = new AddressConverter();

    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<AddressDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(addressConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = addressService.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_CITY)) {
            request.setAttribute(ATTR_SEARCH_CITY,
                    pageableDto.getSearchFields().get(ATTR_CITY));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_STREET)) {
            request.setAttribute(ATTR_SEARCH_STREET,
                    pageableDto.getSearchFields().get(ATTR_STREET));
        }

        return ADDRESSES_JSP;
    }
}
