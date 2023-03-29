package it.academy.controller.commands.purchase;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.PurchaseDto;
import it.academy.services.IPurchaseService;
import it.academy.services.impl.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class UpdatePurchase implements Command {
    private final IPurchaseService service = new PurchaseService();

    private final IConverter<PurchaseDto> converter = new PurchaseConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PurchaseDto purchase = converter.convertToDto(request);
        service.updatePurchase(purchase);

        return request.getParameter(PREV_URL);
    }
}
