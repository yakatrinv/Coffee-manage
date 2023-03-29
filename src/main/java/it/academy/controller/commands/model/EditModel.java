package it.academy.controller.commands.model;

import it.academy.controller.commands.Command;
import it.academy.dto.ModelDto;
import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MODEL;
import static it.academy.utils.Data.EDIT_MODEL_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditModel implements Command {
    private final IModelService service = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ModelDto model;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isBlank(id)) {
            model = service.findModelById(Integer.parseInt(id));
            request.setAttribute(ATTR_MODEL, model);
        }

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_MODEL_JSP;
    }
}
