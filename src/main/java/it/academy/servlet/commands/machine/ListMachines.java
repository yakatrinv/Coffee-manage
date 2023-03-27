package it.academy.servlet.commands.machine;

import it.academy.converter.IConverter;
import it.academy.converter.IMachineConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_SEARCH_SERIAL_NUMBER;
import static it.academy.utils.Data.ATTR_SERIAL_NUMBER;
import static it.academy.utils.Data.MACHINES_JSP;
import static it.academy.utils.Data.PAGEABLE;


public class ListMachines implements Command {
    private final IConverter<Pageable<MachineDto>> converter = new PageableConverter<>();

    private final IMachineConverter machineConverter = new MachineConverter();

    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<MachineDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(machineConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_SERIAL_NUMBER)) {
            request.setAttribute(ATTR_SEARCH_SERIAL_NUMBER,
                    pageableDto.getSearchFields().get(ATTR_SERIAL_NUMBER));
        }

        return MACHINES_JSP;
    }
}
