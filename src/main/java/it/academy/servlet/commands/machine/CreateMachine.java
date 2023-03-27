package it.academy.servlet.commands.machine;

import it.academy.dto.AddressDto;
import it.academy.dto.ModelDto;
import it.academy.services.IAddressService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.ModelService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static it.academy.utils.Data.ADD_MACHINE_JSP;
import static it.academy.utils.Data.PREV_URL;

public class CreateMachine implements Command {
    private final IAddressService addressService = new AddressService();

    private final IModelService modelService = new ModelService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        List<AddressDto> addresses =
                addressService.findAllAddresses();

        List<ModelDto> models = modelService.findAllModels();

        request.setAttribute("models", models);
        request.setAttribute("addresses", addresses);

        return ADD_MACHINE_JSP;
    }
}
