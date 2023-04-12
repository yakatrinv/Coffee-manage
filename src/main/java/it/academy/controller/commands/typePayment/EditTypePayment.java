package it.academy.controller.commands.typePayment;

import it.academy.controller.commands.Command;
import it.academy.dto.TypePaymentDto;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataTypePayment.ATTR_TYPE_PAYMENT;
import static it.academy.utils.DataTypePayment.EDIT_TYPE_PAYMENT_JSP;

public class EditTypePayment implements Command {
    private final ITypePaymentService service = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TypePaymentDto typePayment;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            typePayment = service.findTypePaymentById(Integer.parseInt(id));
            request.setAttribute(ATTR_TYPE_PAYMENT, typePayment);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_TYPE_PAYMENT_JSP;
    }
}
