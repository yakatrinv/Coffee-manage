package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IMachineConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.MachineDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IAddressService;
import it.academy.services.IMachineService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.MachineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.DataAddress.ATTR_CITIES;
import static it.academy.utils.DataAddress.ATTR_SEARCH_CITIES;
import static it.academy.utils.DataAddress.ATTR_SET_SEARCH_CITIES;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataMachine.CUSTOMER_MACHINES_JSP;
import static it.academy.utils.DataPageable.PAGEABLE;
import static it.academy.utils.DataPageable.STRING_SEARCH_CITIES;


public class ChooseMachine implements Command {
    private final IConverter<Pageable<MachineDto>> converterP = new PageableConverter<>();

    private final IMachineConverter converter = new MachineConverter();

    private final IMachineService machineService = new MachineService();

    private final IAddressService addressService = new AddressService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<MachineDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = machineService.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        String[] arrSearchCities = (String[]) pageableDto.getSearchFields().get(ATTR_CITIES);
        List<String> stringSet = arrSearchCities == null ? null : Arrays.stream(arrSearchCities).toList();
        String searchCities = stringSet == null ? null : stringSet.stream()
                .map(str -> STRING_SEARCH_CITIES + str)
                .collect(Collectors.joining());

        request.setAttribute(ATTR_SET_SEARCH_CITIES, stringSet);
        request.setAttribute(ATTR_SEARCH_CITIES, searchCities);

        //list city
        List<String> cities = addressService.findAllCities();
        request.setAttribute(ATTR_CITIES, cities);

        return CUSTOMER_MACHINES_JSP;
    }
}
