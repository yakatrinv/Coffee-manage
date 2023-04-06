package it.academy.controller.commands.machineProduct;

import it.academy.controller.commands.Command;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataMachine.ATTR_MACHINE_ID;
import static it.academy.utils.DataProduct.ADD_MACHINE_PRODUCTS_JSP;
import static it.academy.utils.DataProduct.ATTR_PRODUCTS;

public class CreateMachineProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        request.setAttribute(ATTR_PRODUCTS, service.findAllProducts());
        request.setAttribute(ATTR_MACHINE_ID,
                request.getParameter(ATTR_MACHINE_ID));

        return ADD_MACHINE_PRODUCTS_JSP;
    }
}
