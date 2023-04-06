package it.academy.controller.commands.product;

import it.academy.controller.commands.Command;
import it.academy.dto.ProductDto;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataProduct.ATTR_PRODUCT;
import static it.academy.utils.DataProduct.EDIT_PRODUCT_JSP;

public class EditProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDto product;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            product = service.findProductById(Integer.parseInt(id));
            request.setAttribute(ATTR_PRODUCT, product);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PRODUCT_JSP;
    }
}
