package it.academy.controller.commands.discount;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IDiscountConverter;
import it.academy.converter.impl.DiscountConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.DiscountDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IDiscountService;
import it.academy.services.impl.DiscountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataDiscount.ATTR_PERCENT;
import static it.academy.utils.DataDiscount.ATTR_SEARCH_PERCENT;
import static it.academy.utils.DataDiscount.ATTR_SEARCH_SUM;
import static it.academy.utils.DataDiscount.ATTR_SUM;
import static it.academy.utils.DataDiscount.DISCOUNTS_JSP;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataPageable.PAGEABLE;


public class ListDiscounts implements Command {
    private final IConverter<Pageable<DiscountDto>> converterP = new PageableConverter<>();

    private final IDiscountConverter converter = new DiscountConverter();

    private final IDiscountService service = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<DiscountDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_SUM)) {
            request.setAttribute(ATTR_SEARCH_SUM,
                    pageableDto.getSearchFields().get(ATTR_SUM));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_PERCENT)) {
            request.setAttribute(ATTR_SEARCH_PERCENT,
                    pageableDto.getSearchFields().get(ATTR_PERCENT));
        }

        return DISCOUNTS_JSP;
    }
}
