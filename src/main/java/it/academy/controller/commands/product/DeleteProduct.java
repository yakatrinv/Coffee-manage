package it.academy.controller.commands.product;

import it.academy.controller.commands.Command;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteProduct implements Command {
    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            service.deleteProductById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
