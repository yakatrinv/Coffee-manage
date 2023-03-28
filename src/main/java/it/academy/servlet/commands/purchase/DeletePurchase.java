package it.academy.servlet.commands.purchase;

import it.academy.services.IPurchaseService;
import it.academy.services.impl.PurchaseService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeletePurchase implements Command {
    private final IPurchaseService service = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            service.deletePurchaseById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
