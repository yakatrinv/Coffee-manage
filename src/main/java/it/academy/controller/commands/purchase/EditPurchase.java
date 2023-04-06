package it.academy.controller.commands.purchase;

import it.academy.controller.commands.Command;
import it.academy.dto.PurchaseDto;
import it.academy.services.ICreditCardService;
import it.academy.services.ICustomerService;
import it.academy.services.IDiscountService;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.IPurchaseService;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.CustomerService;
import it.academy.services.impl.DiscountService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import it.academy.services.impl.PurchaseService;
import it.academy.services.impl.TypePaymentService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARDS;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMERS;
import static it.academy.utils.DataDiscount.ATTR_DISCOUNTS;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.PREV_URL;
import static it.academy.utils.DataMachine.ATTR_MACHINES;
import static it.academy.utils.DataProduct.ATTR_PRODUCTS;
import static it.academy.utils.DataPurchase.ATTR_PURCHASE;
import static it.academy.utils.DataPurchase.EDIT_PURCHASE_JSP;
import static it.academy.utils.DataTypePayment.ATTR_TYPE_PAYMENTS;

public class EditPurchase implements Command {
    private final IPurchaseService purchaseService = new PurchaseService();

    private final ICustomerService customerService = new CustomerService();

    private final IMachineService machineService = new MachineService();

    private final IProductService productService = new ProductService();

    private final IDiscountService discountService = new DiscountService();

    private final ITypePaymentService typePaymentService = new TypePaymentService();

    private final ICreditCardService creditCardService = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PurchaseDto purchase;
        String id = request.getParameter(ATTR_ID);
        if (StringUtils.isNotBlank(id)) {
            purchase = purchaseService.findPurchaseById(Integer.parseInt(id));
            request.setAttribute(ATTR_PURCHASE, purchase);
        }

        request.setAttribute(ATTR_CUSTOMERS, customerService.findAllCustomers());
        request.setAttribute(ATTR_MACHINES, machineService.findAllMachines());
        request.setAttribute(ATTR_PRODUCTS, productService.findAllProducts());
        request.setAttribute(ATTR_DISCOUNTS, discountService.findAllDiscounts());
        request.setAttribute(ATTR_TYPE_PAYMENTS, typePaymentService.findAllTypePayments());
        request.setAttribute(ATTR_CREDIT_CARDS, creditCardService.findAllCreditCards());

        request.setAttribute(PREV_URL, request.getParameter(PREV_URL));
        return EDIT_PURCHASE_JSP;
    }
}
