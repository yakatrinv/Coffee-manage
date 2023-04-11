package it.academy.controller.commands.discount;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.DiscountConverter;
import it.academy.dto.DiscountDto;
import it.academy.services.IDiscountService;
import it.academy.services.impl.DiscountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;

public class UpdateDiscount implements Command {
    private final IDiscountService service = new DiscountService();

    private final IConverter<DiscountDto> converter = new DiscountConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DiscountDto discountDto = converter.convertToDto(request);
        service.updateDiscount(discountDto);

        return request.getParameter(PREV_URL);
    }
}
