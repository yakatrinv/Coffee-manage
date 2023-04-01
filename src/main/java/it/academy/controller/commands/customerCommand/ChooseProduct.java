package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IProductConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_MACHINE;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.PAGEABLE;
import static it.academy.utils.DataCustomer.ATTR_SORT_FIELD;
import static it.academy.utils.DataCustomer.CUSTOMER_PRODUCTS_JSP;


public class ChooseProduct implements Command {
    private final IConverter<Pageable<ProductDto>> converterP = new PageableConverter<>();

    private final IMachineService service = new MachineService();

    private final IProductConverter converter = new ProductConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ProductDto> pageableDto = converterP.convertToDto(request);

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(converter.convertSortFields(request));

        String machineId = request.getParameter(ATTR_MACHINE_ID);
        Integer id = (StringUtils.isBlank(machineId)) ?
                null : Integer.parseInt(machineId);

        pageableDto = service.getProducts(id, pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        MachineDto machine = id == null ? null :
                service.findMachineById(id);

        request.setAttribute(ATTR_MACHINE, machine);

        request.setAttribute(ATTR_SORT_FIELD, pageableDto.getSortField());

        return CUSTOMER_PRODUCTS_JSP;
    }
}
