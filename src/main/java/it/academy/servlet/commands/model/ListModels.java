package it.academy.servlet.commands.model;

import it.academy.converter.IConverter;
import it.academy.converter.IModelConverter;
import it.academy.converter.impl.ModelConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.dto.ModelDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_BRAND;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_NAME_MODEL;
import static it.academy.utils.Data.ATTR_SEARCH_BRAND;
import static it.academy.utils.Data.ATTR_SEARCH_NAME_MODEL;
import static it.academy.utils.Data.MODELS_JSP;
import static it.academy.utils.Data.PAGEABLE;


public class ListModels implements Command {
    private final IConverter<Pageable<ModelDto>> converter = new PageableConverter<>();

    private final IModelConverter modelConverter = new ModelConverter();

    private final IModelService service = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Pageable<ModelDto> pageableDto = converter.convertToDto(request);
        pageableDto.setSearchFields(modelConverter.convertSearchFields(request));

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
