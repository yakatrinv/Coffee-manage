package it.academy.controller.commands.model;

import it.academy.controller.commands.Command;
import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteModel implements Command {
    private final IModelService service = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isBlank(id)) {
            service.deleteModelById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
