package it.academy.controller.commands.machine;

import it.academy.controller.commands.Command;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteMachine implements Command {
    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isBlank(id)) {
            service.deleteMachineById(Integer.parseInt(id));
        }
        return request.getParameter(PREV_URL);
    }
}
