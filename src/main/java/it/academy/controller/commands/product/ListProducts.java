package it.academy.controller.commands.product;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IProductConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.ProductConverter;
import it.academy.dto.ProductDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IProductService;
import it.academy.services.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataPageable.ATTR_SORT_FIELD;
import static it.academy.utils.DataPageable.PAGEABLE;
import static it.academy.utils.DataProduct.ATTR_NAME;
import static it.academy.utils.DataProduct.ATTR_PRICE;
import static it.academy.utils.DataProduct.ATTR_SEARCH_NAME;
import static it.academy.utils.DataProduct.ATTR_SEARCH_PRICE;
import static it.academy.utils.DataProduct.PRODUCTS_JSP;


public class ListProducts implements Command {
    private final IConverter<Pageable<ProductDto>> converterP = new PageableConverter<>();

    private final IProductConverter converter = new ProductConverter();

    private final IProductService service = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ProductDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));
        pageableDto.setSortField(converter.convertSortFields(request));

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

        request.setAttribute(ATTR_SORT_FIELD, pageableDto.getSortField());

        return PRODUCTS_JSP;
    }
}
