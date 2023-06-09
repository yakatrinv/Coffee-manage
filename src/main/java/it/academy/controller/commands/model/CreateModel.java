package it.academy.controller.commands.model;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataModel.ADD_MODEL_JSP;

public class CreateModel implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return ADD_MODEL_JSP;
    }
}
