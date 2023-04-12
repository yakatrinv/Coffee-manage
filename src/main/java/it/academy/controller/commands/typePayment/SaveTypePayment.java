package it.academy.controller.commands.typePayment;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.TypePaymentConverter;
import it.academy.dto.TypePaymentDto;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.TypePaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;

public class SaveTypePayment implements Command {
    private final ITypePaymentService service = new TypePaymentService();

    private final IConverter<TypePaymentDto> converter = new TypePaymentConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TypePaymentDto typePaymentDto = converter.convertToDto(request);
        service.createTypePayment(typePaymentDto);

        return request.getParameter(PREV_URL);
    }
}
