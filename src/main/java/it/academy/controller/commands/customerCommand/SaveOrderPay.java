package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.PurchaseDto;
import it.academy.services.IPurchaseService;
import it.academy.services.impl.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.MAIN_JSP;

public class SaveOrderPay implements Command {
    private final IPurchaseService service = new PurchaseService();

    private final IConverter<PurchaseDto> converter = new PurchaseConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PurchaseDto purchase = converter.convertToDto(request);
        service.createPurchase(purchase);

        return MAIN_JSP;
    }
}
