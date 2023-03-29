package it.academy.controller.commands.purchase;

import it.academy.controller.commands.Command;
import it.academy.services.ICustomerService;
import it.academy.services.IDiscountService;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.impl.CustomerService;
import it.academy.services.impl.DiscountService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.Data.ADD_PURCHASE_JSP;
import static it.academy.utils.Data.ATTR_CUSTOMERS;
import static it.academy.utils.Data.ATTR_DISCOUNTS;
import static it.academy.utils.Data.ATTR_MACHINES;
import static it.academy.utils.Data.ATTR_PRODUCTS;
import static it.academy.utils.Data.PREV_URL;

public class CreatePurchase implements Command {
    private final ICustomerService customerService = new CustomerService();

    private final IMachineService machineService = new MachineService();

    private final IProductService productService = new ProductService();

    private final IDiscountService discountService = new DiscountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));

        request.setAttribute(ATTR_CUSTOMERS, customerService.findAllCustomers());
        request.setAttribute(ATTR_MACHINES, machineService.findAllMachines());
        request.setAttribute(ATTR_PRODUCTS, productService.findAllProducts());
        request.setAttribute(ATTR_DISCOUNTS, discountService.findAllDiscounts());

        return ADD_PURCHASE_JSP;
    }
}
