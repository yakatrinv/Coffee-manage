package it.academy.controller.commands.product;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataProduct.ADD_PRODUCT_JSP;

public class CreateProduct implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_PRODUCT_JSP;
    }
}
