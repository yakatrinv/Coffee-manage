package it.academy.controller.commands.machineProduct;

import it.academy.controller.commands.Command;
import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.PREV_URL;

public class SaveMachineProduct implements Command {
    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String machineIdS = request.getParameter(ATTR_MACHINE_ID);
        String productIdS = request.getParameter(ATTR_PRODUCT_ID);

        Integer machineId = (StringUtils.isBlank(machineIdS)) ?
                null : Integer.parseInt(machineIdS);
        Integer productId = (StringUtils.isBlank(productIdS)) ?
                null : Integer.parseInt(productIdS);

        if (machineId != null && productId != null) {
            service.addProductInMachine(machineId, productId);
        }

        return request.getParameter(PREV_URL);
    }
}
