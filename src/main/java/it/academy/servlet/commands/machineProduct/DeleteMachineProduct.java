package it.academy.servlet.commands.machineProduct;

import it.academy.services.IMachineService;
import it.academy.services.impl.MachineService;
import it.academy.servlet.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.PREV_URL;

public class DeleteMachineProduct implements Command {
    private final IMachineService service = new MachineService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String machineIdS = request.getParameter(ATTR_MACHINE_ID);
        String productIdS = request.getParameter(ATTR_PRODUCT_ID);

        Integer machineId = (machineIdS == null || machineIdS.isEmpty()) ?
                null : Integer.valueOf(machineIdS);
        Integer productId = (productIdS == null || productIdS.isEmpty()) ?
                null : Integer.valueOf(productIdS);

        if (machineId != null && productId != null) {
            service.deleteProductInMachine(machineId, productId);
        }

        return request.getParameter(PREV_URL);
    }
}

