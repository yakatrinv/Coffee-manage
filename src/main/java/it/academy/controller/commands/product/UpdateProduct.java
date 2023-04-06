package it.academy.controller.commands.product;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.ProductDto;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.PREV_URL;

public class UpdateProduct implements Command {
    private final IProductService service = new ProductService();

    private final IConverter<ProductDto> converter = new ProductConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDto productDto = converter.convertToDto(request);
        service.updateProduct(productDto);

        return request.getParameter(PREV_URL);
    }
}
