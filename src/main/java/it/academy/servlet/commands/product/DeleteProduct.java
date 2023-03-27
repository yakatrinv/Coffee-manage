package it.academy.servlet.commands.product;

import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            service.deleteProductById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
