package it.academy.servlet.commands.product;

import it.academy.converter.IConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.ProductDto;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class SaveProduct implements Command {
    private final IProductService service = new ProductService();

    private final IConverter<ProductDto> converter = new ProductConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ProductDto productDto = converter.convertToDto(request);
        service.createProduct(productDto);

        return request.getParameter(PREV_URL);
    }
}
