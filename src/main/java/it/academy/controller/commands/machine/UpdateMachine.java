package it.academy.controller.commands.machine;

import it.academy.converter.IConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.dto.MachineDto;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import it.academy.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class UpdateMachine implements Command {
    private final IMachineService service = new MachineService();

    private final IConverter<MachineDto> converter = new MachineConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        MachineDto machine = converter.convertToDto(request);
        service.updateMachine(machine);

        return request.getParameter(PREV_URL);
    }
}
