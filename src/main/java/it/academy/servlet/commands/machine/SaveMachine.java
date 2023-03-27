package it.academy.servlet.commands.machine;

import it.academy.converter.IConverter;
import it.academy.converter.impl.MachineConverter;
import it.academy.dto.MachineDto;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.PREV_URL;

public class SaveMachine implements Command {
    private final IMachineService service = new MachineService();

    private final IConverter<MachineDto> converter = new MachineConverter();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        MachineDto entity = converter.convertToDto(request);
        service.createMachine(entity);

        return request.getParameter(PREV_URL);
    }
}
