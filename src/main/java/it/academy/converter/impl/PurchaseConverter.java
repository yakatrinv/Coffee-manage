package it.academy.converter.impl;

import it.academy.converter.IPurchaseConverter;
import it.academy.dto.CustomerDto;
import it.academy.dto.DiscountDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.PurchaseDto;
import it.academy.services.ICustomerService;
import it.academy.services.IDiscountService;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.impl.CustomerService;
import it.academy.services.impl.DiscountService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_CUSTOMER_ID;
import static it.academy.utils.Data.ATTR_DISCOUNT_ID;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MACHINE_ID;
import static it.academy.utils.Data.ATTR_PRICE;
import static it.academy.utils.Data.ATTR_PRODUCT_ID;
import static it.academy.utils.Data.ATTR_SEARCH_PRICE;
import static it.academy.utils.Data.ATTR_SEARCH_SUM;
import static it.academy.utils.Data.ATTR_SUM;
import static it.academy.utils.Data.VALUE_ZERO;

public class PurchaseConverter implements IPurchaseConverter {
    ICustomerService customerService = new CustomerService();

    IMachineService machineService = new MachineService();

    IProductService productService = new ProductService();

    IDiscountService discountService = new DiscountService();

    @Override
    public PurchaseDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String customerId = request.getParameter(ATTR_CUSTOMER_ID);
        String machineId = request.getParameter(ATTR_MACHINE_ID);
        String productId = request.getParameter(ATTR_PRODUCT_ID);
        String price = request.getParameter(ATTR_PRICE);
        String discountId = request.getParameter(ATTR_DISCOUNT_ID);
        String sum = request.getParameter(ATTR_SUM);

        CustomerDto customer = null;
        if (StringUtils.isNotBlank(customerId)) {
            customer = customerService.findCustomerById(Integer.parseInt(customerId));
        }

        MachineDto machine = null;
        if (StringUtils.isNotBlank(machineId)) {
            machine = machineService.findMachineById(Integer.parseInt(machineId));
        }

        ProductDto product = null;
        if (StringUtils.isNotBlank(productId)) {
            product = productService.findProductById(Integer.parseInt(productId));
        }

        DiscountDto discount = null;
        if (StringUtils.isNotBlank(discountId)) {
            discount = discountService.findDiscountById(Integer.parseInt(discountId));
        }

        return PurchaseDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .customer(customer)
                .machine(machine)
                .product(product)
                .price(StringUtils.isBlank(price) ? VALUE_ZERO : Float.parseFloat(price))
                .discount(discount)
                .sum(StringUtils.isBlank(sum) ? VALUE_ZERO : Float.parseFloat(sum))
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String price = request.getParameter(ATTR_SEARCH_PRICE);
        String sum = request.getParameter(ATTR_SEARCH_SUM);

        searchFields.put(ATTR_PRICE, StringUtils.isBlank(price) ? null : Float.parseFloat(price));
        searchFields.put(ATTR_SUM, StringUtils.isBlank(sum) ? null : Float.parseFloat(sum));

        return searchFields;
    }
}
