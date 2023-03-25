package it.academy.servlet.commands.auth;

import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.REGISTRATION_JSP;

public class GetRegPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return REGISTRATION_JSP;
    }
}


