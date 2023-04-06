package it.academy.controller.commands.auth;

import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAuth.LOGIN_JSP;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return LOGIN_JSP;
    }
}
