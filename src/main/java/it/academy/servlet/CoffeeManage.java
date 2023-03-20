package it.academy.servlet;


import it.academy.services.IAddressService;
import it.academy.services.impl.AddressService;
import it.academy.servlet.commands.Command;
import it.academy.servlet.commands.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/coffee-manage"})
public class CoffeeManage extends HttpServlet {
    CommandFactory receiver = CommandFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("command");
        Command command = receiver.getCommand(value);
        String path = command.execute(request, response);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
