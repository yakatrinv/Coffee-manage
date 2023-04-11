package it.academy.controller.commands.role;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.ADD_ROLE_JSP;
import static it.academy.utils.DataGeneral.PREV_URL;

public class CreateRole implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_ROLE_JSP;
    }
}
