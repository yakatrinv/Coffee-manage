package it.academy.controller.commands.purchase;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IPurchaseConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.PurchaseDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IPurchaseService;
import it.academy.services.impl.PurchaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_PRICE;
import static it.academy.utils.Data.ATTR_SEARCH_PRICE;
import static it.academy.utils.Data.ATTR_SEARCH_SUM;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.PAGEABLE;
import static it.academy.utils.Data.PURCHASES_JSP;


public class ListPurchase implements Command {
    private final IConverter<Pageable<PurchaseDto>> converter = new PageableConverter<>();

    private final IPurchaseConverter purchaseConverter = new PurchaseConverter();

    private final IPurchaseService service = new PurchaseService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<PurchaseDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(purchaseConverter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_PRICE)) {
            request.setAttribute(ATTR_SEARCH_PRICE,
                    pageableDto.getSearchFields().get(ATTR_PRICE));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_SUM)) {
            request.setAttribute(ATTR_SEARCH_SUM,
                    pageableDto.getSearchFields().get(ATTR_SUM));
        }

        return PURCHASES_JSP;
    }
}
