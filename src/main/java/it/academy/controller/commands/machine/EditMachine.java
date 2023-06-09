package it.academy.controller.commands.machine;

import it.academy.controller.commands.Command;
import it.academy.dto.MachineDto;
import it.academy.services.IAddressService;
import it.academy.services.IMachineService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ModelService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataAddress.ATTR_ADDRESSES;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataMachine.ATTR_MACHINE;
import static it.academy.utils.DataMachine.EDIT_MACHINE_JSP;
import static it.academy.utils.DataModel.ATTR_MODELS;

public class EditMachine implements Command {
    private final IMachineService service = new MachineService();

    private final IAddressService addressService = new AddressService();

    private final IModelService modelService = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        MachineDto machine;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            machine = service.findMachineById(Integer.parseInt(id));
            request.setAttribute(ATTR_MACHINE, machine);
        }

        request.setAttribute(ATTR_MODELS, modelService.findAllModels());
        request.setAttribute(ATTR_ADDRESSES, addressService.findAllAddresses());

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_MACHINE_JSP;
    }
}
