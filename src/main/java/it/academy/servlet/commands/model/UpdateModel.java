package it.academy.servlet.commands.model;

import it.academy.converter.IConverter;
import it.academy.converter.impl.ModelConverter;
import it.academy.dto.ModelDto;
import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class UpdateModel implements Command {
    private final IModelService service = new ModelService();

    private final IConverter<ModelDto> converter = new ModelConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ModelDto modelDto = converter.convertToDto(request);
        service.updateModel(modelDto);

        return request.getParameter(PREV_URL);
    }
}
