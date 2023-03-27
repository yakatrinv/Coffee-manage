package it.academy.servlet.commands.product;

import it.academy.dto.ProductDto;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_PRODUCT;
import static it.academy.utils.Data.EDIT_PRODUCT_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDto product;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            product = service.findProductById(Integer.valueOf(id));
            request.setAttribute(ATTR_PRODUCT, product);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PRODUCT_JSP;
    }
}
