package it.academy.servlet.commands.auth;

import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.LOGIN_JSP;

public class GetLoginPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_JSP;
    }
}


