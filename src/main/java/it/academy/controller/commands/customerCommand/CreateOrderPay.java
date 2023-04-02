package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.TypePaymentDto;
import it.academy.models.Discount;
import it.academy.services.ICreditCardService;
import it.academy.services.IDiscountService;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.IPurchaseService;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.DiscountService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import it.academy.services.impl.PurchaseService;
import it.academy.services.impl.TypePaymentService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

import static it.academy.utils.Data.ATTR_CREDIT_CARDS;
import static it.academy.utils.Data.ATTR_DISCOUNT;
import static it.academy.utils.Data.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.Data.ATTR_MACHINE;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRODUCT;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.ATTR_TYPE_PAYMENT;
import static it.academy.utils.Data.PREV_URL;
import static it.academy.utils.DataCustomer.CUSTOMER_ORDER_PAY_JSP;

public class CreateOrderPay implements Command {
    private final IMachineService machineService = new MachineService();

    private final IProductService productService = new ProductService();

    private final IPurchaseService purchaseService = new PurchaseService();

    private final IDiscountService discountService = new DiscountService();

    private final ICreditCardService creditCardService = new CreditCardService();

    private final ITypePaymentService typePaymentService = new TypePaymentService();

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


        List<TypePaymentDto> allTypePayments = typePaymentService.findAllTypePayments();
        List<CreditCardDto> allCreditCards = creditCardService.findAllCreditCards();

        //рассчитать возможность предоставления скидки
        Serializable id = ((CustomerDto) request.getSession().getAttribute(ATTR_LOGGED_CUSTOMER)).getId();
        Float sumAllPurchases = purchaseService.getSumPurchases(id);

        Discount discount = discountService.getPercentDiscount(sumAllPurchases);

        float sumPurchase = 0f;
        if (product != null) {
            float percent = (float) (discount == null ? 0 : discount.getPercent());
            sumPurchase = getSumPurchase(product.getPrice(), percent);
        }


        request.setAttribute(ATTR_MACHINE, machine);
        request.setAttribute(ATTR_PRODUCT, product);
        request.setAttribute(ATTR_TYPE_PAYMENT, allTypePayments);
        request.setAttribute(ATTR_CREDIT_CARDS, allCreditCards);
        request.setAttribute(ATTR_DISCOUNT, discount);
        request.setAttribute(ATTR_SUM, sumPurchase);

        return CUSTOMER_ORDER_PAY_JSP;
    }

    private static float getSumPurchase(float price, float percent) {
        float sumDiscount = (percent / 100) * price;
        return (float) (Math.ceil(((price - sumDiscount) * 100)) / 100);
    }
}
