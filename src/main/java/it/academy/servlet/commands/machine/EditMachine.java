package it.academy.servlet.commands.machine;

import it.academy.dto.AddressDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ModelDto;
import it.academy.services.IAddressService;
import it.academy.services.IMachineService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ModelService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static it.academy.utils.Data.ATTR_ADDRESSES;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MACHINE;
import static it.academy.utils.Data.ATTR_MODELS;
import static it.academy.utils.Data.EDIT_MACHINE_JSP;
import static it.academy.utils.Data.PREV_URL;

public class EditMachine implements Command {
    private final IMachineService service = new MachineService();

    private final IAddressService addressService = new AddressService();

    private final IModelService modelService = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        MachineDto entity;
        String id = request.getParameter(ATTR_ID);
        if (id != null) {
            entity = service.findMachineById(Integer.valueOf(id));
            request.setAttribute(ATTR_MACHINE, entity);
        }

        List<AddressDto> addresses =
                addressService.findAllAddresses();

        List<ModelDto> models = modelService.findAllModels();

        request.setAttribute(ATTR_MODELS, models);
        request.setAttribute(ATTR_ADDRESSES, addresses);

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_MACHINE_JSP;
    }
}
