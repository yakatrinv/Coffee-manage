package it.academy.servlet.commands.machineProduct;

import it.academy.dto.ProductDto;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Data.ADD_MACHINE_PRODUCTS_JSP;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRODUCTS;
import static it.academy.utils.Data.PREV_URL;

public class CreateMachineProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        List<ProductDto> products =
                service.findAllProducts();

        request.setAttribute(ATTR_PRODUCTS, products);
        request.setAttribute(ATTR_MACHINE_ID,
                request.getParameter(ATTR_MACHINE_ID));

        return ADD_MACHINE_PRODUCTS_JSP;
    }
}
