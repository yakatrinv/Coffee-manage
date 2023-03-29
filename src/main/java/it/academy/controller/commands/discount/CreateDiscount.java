package it.academy.controller.commands.discount;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADD_DISCOUNT_JSP;
import static it.academy.utils.Data.PREV_URL;

public class CreateDiscount implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_DISCOUNT_JSP;
    }
}
