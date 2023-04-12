package it.academy.controller.commands.typePayment;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataTypePayment.ADD_TYPE_PAYMENT_JSP;

public class CreateTypePayment implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_TYPE_PAYMENT_JSP;
    }
}
