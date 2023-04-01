package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ATTR_MACHINE;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRODUCT;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.PREV_URL;
import static it.academy.utils.DataCustomer.CUSTOMER_ORDER_PAY_JSP;

public class CreateOrderPay implements Command {
    private final IMachineService machineService = new MachineService();
    private final IProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        String machineIdS = request.getParameter(ATTR_MACHINE_ID);
        Integer machineId = (StringUtils.isBlank(machineIdS)) ?
                null : Integer.parseInt(machineIdS);

        MachineDto machine = machineId == null ? null :
                machineService.findMachineById(machineId);

        String productIdS = request.getParameter(ATTR_PRODUCT_ID);
        Integer productId = (StringUtils.isBlank(productIdS)) ?
                null : Integer.parseInt(productIdS);

        ProductDto product = productId == null ? null :
                productService.findProductById(productId);


        //рассчитать возможность предоставления скидки

        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(ATTR_PRODUCT, product);

        return CUSTOMER_ORDER_PAY_JSP;
    }
}
