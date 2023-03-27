package it.academy.servlet.commands.model;

import it.academy.services.IModelService;
import it.academy.services.impl.ModelService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteModel implements Command {
    private final IModelService service = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            service.deleteModelById(Integer.valueOf(id));
        }
        return request.getParameter(PREV_URL);
    }
}
