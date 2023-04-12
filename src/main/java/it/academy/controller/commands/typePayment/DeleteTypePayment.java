package it.academy.controller.commands.typePayment;

import it.academy.controller.commands.Command;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteTypePayment implements Command {
    private final ITypePaymentService service = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            service.deleteTypePaymentById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
