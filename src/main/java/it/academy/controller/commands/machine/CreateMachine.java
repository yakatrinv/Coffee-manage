package it.academy.controller.commands.machine;

import it.academy.controller.commands.Command;
import it.academy.services.IAddressService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.ModelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADD_MACHINE_JSP;
import static it.academy.utils.Data.ATTR_ADDRESSES;
import static it.academy.utils.Data.ATTR_MODELS;
import static it.academy.utils.Data.PREV_URL;

public class CreateMachine implements Command {
    private final IAddressService addressService = new AddressService();

    private final IModelService modelService = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        request.setAttribute(ATTR_MODELS, modelService.findAllModels());
        request.setAttribute(ATTR_ADDRESSES, addressService.findAllAddresses());

        return ADD_MACHINE_JSP;
    }
}
