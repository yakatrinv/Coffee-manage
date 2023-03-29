package it.academy.controller.commands.machineProduct;

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
import java.util.List;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MACHINE;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.LIST_MACHINES;
import static it.academy.utils.Data.MACHINE_PRODUCTS_JSP;
import static it.academy.utils.Data.PAGEABLE;


public class ListMachineProduct implements Command {
    private final IConverter<Pageable<ProductDto>> converterP = new PageableConverter<>();

    private final IProductConverter converter = new ProductConverter();

    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ProductDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        String machineId = request.getParameter(ATTR_MACHINE_ID);
        Integer id = (StringUtils.isBlank(machineId)) ?
                null : Integer.parseInt(machineId);

        pageableDto = service.getProducts(id, pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        List<MachineDto> machines = service.findAllMachines();
        MachineDto machine = id == null ? null :
                service.findMachineById(id);

        request.setAttribute(LIST_MACHINES, machines);
        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(ATTR_MACHINE_ID, machineId);

        return MACHINE_PRODUCTS_JSP;
    }
}
