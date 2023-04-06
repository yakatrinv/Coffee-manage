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
import it.academy.services.IProductService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataMachine.ATTR_MACHINE;
import static it.academy.utils.DataMachine.ATTR_MACHINE_ID;
import static it.academy.utils.DataPageable.ATTR_SORT_FIELD;
import static it.academy.utils.DataPageable.PAGEABLE;
import static it.academy.utils.DataProduct.CUSTOMER_PRODUCTS_JSP;


public class ChooseProduct implements Command {
    private final IConverter<Pageable<ProductDto>> converterP = new PageableConverter<>();

    private final IProductConverter converter = new ProductConverter();

    private final IMachineService machineService = new MachineService();

    private final IProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ProductDto> pageableDto = converterP.convertToDto(request);

        pageableDto.setSortField(converter.convertSortFields(request));

        String machineId = request.getParameter(ATTR_MACHINE_ID);
        Integer id = (StringUtils.isBlank(machineId)) ?
                null : Integer.parseInt(machineId);

        pageableDto = productService.getProductsMachine(id, pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        MachineDto machine = id == null ? null :
                machineService.findMachineById(id);

        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(ATTR_SORT_FIELD, pageableDto.getSortField());

        return CUSTOMER_PRODUCTS_JSP;
    }
}
