package it.academy.converter.impl;

import it.academy.converter.IPurchaseConverter;
import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.DiscountDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.PurchaseDto;
import it.academy.dto.TypePaymentDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.CustomerMapper;
import it.academy.mappers.impl.MachineMapper;
import it.academy.mappers.impl.TypePaymentMapper;
import it.academy.models.Customer;
import it.academy.models.Machine;
import it.academy.models.TypePayment;
import it.academy.services.ICreditCardService;
import it.academy.services.ICustomerService;
import it.academy.services.IDiscountService;
import it.academy.services.IMachineService;
import it.academy.services.IProductService;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.CreditCardService;
import it.academy.services.impl.CustomerService;
import it.academy.services.impl.DiscountService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.ProductService;
import it.academy.services.impl.TypePaymentService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER_ID;
import static it.academy.utils.DataDiscount.ATTR_DISCOUNT_ID;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.START_DATE;
import static it.academy.utils.DataGeneral.VALUE_ZERO;
import static it.academy.utils.DataMachine.ATTR_MACHINE_ID;
import static it.academy.utils.DataPageable.ATTR_SORT_FIELD;
import static it.academy.utils.DataProduct.ATTR_PRODUCT_ID;
import static it.academy.utils.DataPurchase.ATTR_CREATE_DATE;
import static it.academy.utils.DataPurchase.ATTR_CUSTOMER;
import static it.academy.utils.DataPurchase.ATTR_MACHINE;
import static it.academy.utils.DataPurchase.ATTR_PRICE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_CUSTOMER;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_FINISH_DATE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MACHINE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MAX_SUM;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MIN_SUM;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_START_DATE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_TYPE_PAYMENT;
import static it.academy.utils.DataPurchase.ATTR_SUM;
import static it.academy.utils.DataPurchase.ATTR_TYPE_PAYMENT;
import static it.academy.utils.DataTypePayment.ATTR_TYPE_PAYMENT_ID;

public class PurchaseConverter implements IPurchaseConverter {
    private final ICustomerService customerService = new CustomerService();

    private final IMachineService machineService = new MachineService();

    private final IProductService productService = new ProductService();

    private final IDiscountService discountService = new DiscountService();

    private final ITypePaymentService typePaymentService = new TypePaymentService();

    private final ICreditCardService creditCardService = new CreditCardService();

    private final Mapper<Customer, CustomerDto> customerMapper = new CustomerMapper();

    private final Mapper<Machine, MachineDto> machineMapper = new MachineMapper();

    private final Mapper<TypePayment, TypePaymentDto> typePaymentMapper = new TypePaymentMapper();

    @Override
    public PurchaseDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String customerId = request.getParameter(ATTR_CUSTOMER_ID);
        String machineId = request.getParameter(ATTR_MACHINE_ID);
        String productId = request.getParameter(ATTR_PRODUCT_ID);
        String price = request.getParameter(ATTR_PRICE);
        String discountId = request.getParameter(ATTR_DISCOUNT_ID);
        String sum = request.getParameter(ATTR_SUM);
        String creditCardId = request.getParameter(ATTR_CREDIT_CARD_ID);
        String typePaymentId = request.getParameter(ATTR_TYPE_PAYMENT_ID);

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

        TypePaymentDto typePayment = null;
        CreditCardDto creditCard = null;
        if (StringUtils.isNotBlank(typePaymentId)) {
            typePayment = typePaymentService.findTypePaymentById(Integer.parseInt(typePaymentId));

            if (typePayment != null &&
                    typePayment.getUseCreditCard() != null &&
                    typePayment.getUseCreditCard()) {
                if (StringUtils.isNotBlank(creditCardId)) {
                    creditCard = creditCardService.findCreditCardById(Integer.parseInt(creditCardId));
                }
            }
        }

        return PurchaseDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .customer(customer)
                .machine(machine)
                .product(product)
                .price(StringUtils.isBlank(price) ? VALUE_ZERO : Float.parseFloat(price))
                .discount(discount)
                .sum(StringUtils.isBlank(sum) ? VALUE_ZERO : Float.parseFloat(sum))
                .typePayment(typePayment)
                .creditCard(creditCard)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();
        LocalDateTime[] dates = new LocalDateTime[2];
        Float[] sumRange = new Float[2];

        String startDateS = request.getParameter(ATTR_SEARCH_START_DATE);
        String finishDateS = request.getParameter(ATTR_SEARCH_FINISH_DATE);
        String customerId = request.getParameter(ATTR_SEARCH_CUSTOMER);
        String machineId = request.getParameter(ATTR_SEARCH_MACHINE);
        String typePaymentId = request.getParameter(ATTR_SEARCH_TYPE_PAYMENT);
        String minSum = request.getParameter(ATTR_SEARCH_MIN_SUM);
        String maxSum = request.getParameter(ATTR_SEARCH_MAX_SUM);

        if (StringUtils.isNotBlank(startDateS)) {
            LocalDate localDate = LocalDate.parse(startDateS);
            dates[0] = LocalDateTime.of(localDate, LocalTime.MIN);
        } else {
            dates[0] = START_DATE;
        }

        if (StringUtils.isNotBlank(finishDateS)) {
            LocalDate localDate = LocalDate.parse(finishDateS);
            dates[1] = LocalDateTime.of(localDate, LocalTime.MAX);
        } else {
            dates[1] = LocalDateTime.now();
        }

        CustomerDto customer = null;
        if (StringUtils.isNotBlank(customerId)) {
            customer = customerService.findCustomerById(Integer.parseInt(customerId));
        }

        MachineDto machine = null;
        if (StringUtils.isNotBlank(machineId)) {
            machine = machineService.findMachineById(Integer.parseInt(machineId));
        }

        TypePaymentDto typePayment = null;
        if (StringUtils.isNotBlank(typePaymentId)) {
            typePayment = typePaymentService.findTypePaymentById(Integer.parseInt(typePaymentId));
        }

        if (StringUtils.isNotBlank(minSum)) {
            sumRange[0] = Float.parseFloat(minSum);
        } else {
            sumRange[0] = 0f;
        }

        if (StringUtils.isNotBlank(maxSum)) {
            sumRange[1] = Float.parseFloat(maxSum);
        } else {
            sumRange[1] = 0f;
        }

        searchFields.put(ATTR_CREATE_DATE, dates);
        searchFields.put(ATTR_CUSTOMER, customerMapper.dtoToEntity(customer));
        searchFields.put(ATTR_MACHINE, machineMapper.dtoToEntity(machine));
        searchFields.put(ATTR_TYPE_PAYMENT, typePaymentMapper.dtoToEntity(typePayment));
        searchFields.put(ATTR_SUM, sumRange);

        return searchFields;
    }

    @Override
    public String convertSortFields(HttpServletRequest request) {
        String sortField = request.getParameter(ATTR_SORT_FIELD);
        return StringUtils.isBlank(sortField) ? ATTR_ID : sortField;
    }
}
