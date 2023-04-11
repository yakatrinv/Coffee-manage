package it.academy.controller.commands.discount;

import it.academy.controller.commands.Command;
import it.academy.services.IDiscountService;
import it.academy.services.impl.DiscountService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteDiscount implements Command {
    private final IDiscountService service = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            service.deleteDiscountById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
