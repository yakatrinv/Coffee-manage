package it.academy.servlet.commands.product;

import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADD_PRODUCT_JSP;
import static it.academy.utils.Data.PREV_URL;

public class CreateProduct implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_PRODUCT_JSP;
    }
}
