package it.academy.controller.commands.purchase;

import it.academy.controller.commands.Command;
import it.academy.services.IPurchaseService;
import it.academy.services.impl.PurchaseService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeletePurchase implements Command {
    private final IPurchaseService service = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            service.deletePurchaseById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
