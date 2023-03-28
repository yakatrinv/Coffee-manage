package it.academy.servlet.commands.discount;

import it.academy.dto.DiscountDto;
import it.academy.services.IDiscountService;
import it.academy.services.impl.DiscountService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_DISCOUNT;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.EDIT_DISCOUNT_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditDiscount implements Command {
    private final IDiscountService service = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DiscountDto discount;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            discount = service.findDiscountById(Integer.valueOf(id));
            request.setAttribute(ATTR_DISCOUNT, discount);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_DISCOUNT_JSP;
    }
}
