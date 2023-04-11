package it.academy.controller.commands.model;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IModelConverter;
import it.academy.converter.impl.ModelConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.ModelDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataModel.ATTR_BRAND;
import static it.academy.utils.DataModel.ATTR_NAME_MODEL;
import static it.academy.utils.DataModel.ATTR_SEARCH_BRAND;
import static it.academy.utils.DataModel.ATTR_SEARCH_NAME_MODEL;
import static it.academy.utils.DataModel.MODELS_JSP;
import static it.academy.utils.DataPageable.PAGEABLE;


public class ListModels implements Command {
    private final IConverter<Pageable<ModelDto>> converterP = new PageableConverter<>();

    private final IModelConverter converter = new ModelConverter();

    private final IModelService service = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ModelDto> pageableDto = converterP.convertToDto(request);
        pageableDto.setSearchFields(converter.convertSearchFields(request));

        //заглушка
        //добавить возможность сортировки по полям
        pageableDto.setSortField(ATTR_ID);

        pageableDto = service.getPageableRecords(pageableDto);
        request.setAttribute(PAGEABLE, pageableDto);

        if (pageableDto.getSearchFields().containsKey(ATTR_BRAND)) {
            request.setAttribute(ATTR_SEARCH_BRAND,
                    pageableDto.getSearchFields().get(ATTR_BRAND));
        }

        if (pageableDto.getSearchFields().containsKey(ATTR_NAME_MODEL)) {
            request.setAttribute(ATTR_SEARCH_NAME_MODEL,
                    pageableDto.getSearchFields().get(ATTR_NAME_MODEL));
        }

        return MODELS_JSP;
    }
}
