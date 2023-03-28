package it.academy.servlet.commands.discount;

import it.academy.services.IDiscountService;
import it.academy.services.impl.DiscountService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteDiscount implements Command {
    private final IDiscountService service = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            service.deleteDiscountById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
