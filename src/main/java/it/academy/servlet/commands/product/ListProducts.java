package it.academy.servlet.commands.product;

import it.academy.converter.IConverter;
import it.academy.converter.IProductConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME;
import static it.academy.utils.Data.ATTR_PRICE;
import static it.academy.utils.Data.ATTR_SEARCH_NAME;
import static it.academy.utils.Data.ATTR_SEARCH_PRICE;
import static it.academy.utils.Data.PAGEABLE;
import static it.academy.utils.Data.PRODUCTS_JSP;


public class ListProducts implements Command {
    private final IConverter<Pageable<ProductDto>> converter = new PageableConverter<>();

    private final IProductConverter productConverter = new ProductConverter();

    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ProductDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(productConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_NAME)) {
            request.setAttribute(ATTR_SEARCH_NAME,
                    pageableDto.getSearchFields().get(ATTR_NAME));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_PRICE)) {
            request.setAttribute(ATTR_SEARCH_PRICE,
                    pageableDto.getSearchFields().get(ATTR_PRICE));
        }

        return PRODUCTS_JSP;
    }
}
