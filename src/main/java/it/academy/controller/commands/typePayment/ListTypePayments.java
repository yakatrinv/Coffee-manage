package it.academy.controller.commands.typePayment;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.ITypePaymentConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.TypePaymentConverter;
import it.academy.dto.TypePaymentDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.TypePaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataPageable.PAGEABLE;
import static it.academy.utils.DataTypePayment.TYPE_PAYMENTS_JSP;


public class ListTypePayments implements Command {
    private final IConverter<Pageable<TypePaymentDto>> converterP = new PageableConverter<>();

    private final ITypePaymentConverter converter = new TypePaymentConverter();

    private final ITypePaymentService service = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<TypePaymentDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        return TYPE_PAYMENTS_JSP;
    }
}
