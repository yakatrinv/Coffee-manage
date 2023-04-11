package it.academy.controller.commands.machine;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IMachineConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAddress.ATTR_CITY;
import static it.academy.utils.DataAddress.ATTR_SEARCH_CITY;
import static it.academy.utils.DataAddress.ATTR_SEARCH_STREET;
import static it.academy.utils.DataAddress.ATTR_STREET;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataMachine.ATTR_SEARCH_SERIAL_NUMBER;
import static it.academy.utils.DataMachine.ATTR_SERIAL_NUMBER;
import static it.academy.utils.DataMachine.MACHINES_JSP;
import static it.academy.utils.DataModel.ATTR_BRAND;
import static it.academy.utils.DataModel.ATTR_NAME_MODEL;
import static it.academy.utils.DataModel.ATTR_SEARCH_BRAND;
import static it.academy.utils.DataModel.ATTR_SEARCH_NAME_MODEL;
import static it.academy.utils.DataPageable.PAGEABLE;


public class ListMachines implements Command {
    private final IConverter<Pageable<MachineDto>> converterP = new PageableConverter<>();

    private final IMachineConverter converter = new MachineConverter();

    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<MachineDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_SERIAL_NUMBER)) {
            request.setAttribute(ATTR_SEARCH_SERIAL_NUMBER,
                    pageableDto.getSearchFields().get(ATTR_SERIAL_NUMBER));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_BRAND)) {
            request.setAttribute(ATTR_SEARCH_BRAND,
                    pageableDto.getSearchFields().get(ATTR_BRAND));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_NAME_MODEL)) {
            request.setAttribute(ATTR_SEARCH_NAME_MODEL,
                    pageableDto.getSearchFields().get(ATTR_NAME_MODEL));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_CITY)) {
            request.setAttribute(ATTR_SEARCH_CITY,
                    pageableDto.getSearchFields().get(ATTR_CITY));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_STREET)) {
            request.setAttribute(ATTR_SEARCH_STREET,
                    pageableDto.getSearchFields().get(ATTR_STREET));
        }

        return MACHINES_JSP;
    }
}
