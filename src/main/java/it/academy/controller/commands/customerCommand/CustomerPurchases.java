package it.academy.controller.commands.customerCommand;

import it.academy.controller.commands.Command;
import it.academy.converter.IConverter;
import it.academy.converter.IPurchaseConverter;
import it.academy.converter.impl.PageableConverter;
import it.academy.converter.impl.PurchaseConverter;
import it.academy.dto.CustomerDto;
import it.academy.dto.PurchaseDto;
import it.academy.models.pageable.Pageable;
import it.academy.services.ICustomerService;
import it.academy.services.IMachineService;
import it.academy.services.IPurchaseService;
import it.academy.services.ITypePaymentService;
import it.academy.services.impl.CustomerService;
import it.academy.services.impl.MachineService;
import it.academy.services.impl.PurchaseService;
import it.academy.services.impl.TypePaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static it.academy.utils.DataAuth.ATTR_LOGGED_CUSTOMER;
import static it.academy.utils.DataCustomer.ATTR_CUSTOMER_ID;
import static it.academy.utils.DataMachine.ATTR_MACHINES;
import static it.academy.utils.DataPageable.ATTR_SORT_FIELD;
import static it.academy.utils.DataPageable.PAGEABLE;
import static it.academy.utils.DataPurchase.ATTR_CREATE_DATE;
import static it.academy.utils.DataPurchase.ATTR_CUSTOMER;
import static it.academy.utils.DataPurchase.ATTR_MACHINE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_CUSTOMER;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_FINISH_DATE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MACHINE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MAX_SUM;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_MIN_SUM;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_START_DATE;
import static it.academy.utils.DataPurchase.ATTR_SEARCH_TYPE_PAYMENT;
import static it.academy.utils.DataPurchase.ATTR_SUM;
import static it.academy.utils.DataPurchase.ATTR_TYPE_PAYMENT;
import static it.academy.utils.DataPurchase.CUSTOMER_PURCHASES;
import static it.academy.utils.DataPurchase.CUSTOMER_PURCHASES_JSP;
import static it.academy.utils.DataPurchase.PURCHASES_JSP;
import static it.academy.utils.DataTypePayment.ATTR_TYPE_PAYMENTS;


public class CustomerPurchases implements Command {
    private final IConverter<Pageable<PurchaseDto>> converter = new PageableConverter<>();

    private final IPurchaseConverter purchaseConverter = new PurchaseConverter();

    private final IPurchaseService service = new PurchaseService();

    private final IMachineService machineService = new MachineService();

    private final ITypePaymentService typePaymentService = new TypePaymentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CustomerDto loggedCustomer = (CustomerDto) request.getSession().getAttribute(ATTR_LOGGED_CUSTOMER);

        if (loggedCustomer != null) {
            Pageable<PurchaseDto> pageableDto = converter.convertToDto(request);
            pageableDto.setSearchFields(purchaseConverter.convertSearchFields(request));
            pageableDto.setSortField(purchaseConverter.convertSortFields(request));

            pageableDto = service.getPageableRecords(pageableDto);
            request.setAttribute(PAGEABLE, pageableDto);

            if (pageableDto.getSearchFields().containsKey(ATTR_CUSTOMER)) {
                request.setAttribute(ATTR_SEARCH_CUSTOMER,
                        pageableDto.getSearchFields().get(ATTR_CUSTOMER));
            }

            if (pageableDto.getSearchFields().containsKey(ATTR_CREATE_DATE)) {
                LocalDateTime[] dates = (LocalDateTime[]) pageableDto.getSearchFields().get(ATTR_CREATE_DATE);
                LocalDate searchStartDate = LocalDate.from(dates[0]);
                LocalDate searchFinishDate = LocalDate.from(dates[1]);
                request.setAttribute(ATTR_SEARCH_START_DATE, searchStartDate);
                request.setAttribute(ATTR_SEARCH_FINISH_DATE, searchFinishDate);
            }


            if (pageableDto.getSearchFields().containsKey(ATTR_MACHINE)) {
                request.setAttribute(ATTR_SEARCH_MACHINE,
                        pageableDto.getSearchFields().get(ATTR_MACHINE));
            }

            if (pageableDto.getSearchFields().containsKey(ATTR_TYPE_PAYMENT)) {
                request.setAttribute(ATTR_SEARCH_TYPE_PAYMENT,
                        pageableDto.getSearchFields().get(ATTR_TYPE_PAYMENT));
            }

            if (pageableDto.getSearchFields().containsKey(ATTR_SUM)) {
                Float[] sumRange = (Float[]) pageableDto.getSearchFields().get(ATTR_SUM);
                request.setAttribute(ATTR_SEARCH_MIN_SUM, sumRange[0]);
                request.setAttribute(ATTR_SEARCH_MAX_SUM, sumRange[1]);
            }

            request.setAttribute(ATTR_MACHINES, machineService.findAllMachines());
            request.setAttribute(ATTR_TYPE_PAYMENTS, typePaymentService.findAllTypePayments());

            request.setAttribute(ATTR_SORT_FIELD, pageableDto.getSortField());
        }

        return CUSTOMER_PURCHASES_JSP;
    }
}
