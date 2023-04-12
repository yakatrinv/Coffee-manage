package it.academy.converter.impl;

import it.academy.converter.ITypePaymentConverter;
import it.academy.dto.TypePaymentDto;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataGeneral.ON;
import static it.academy.utils.DataTypePayment.ATTR_NAME;
import static it.academy.utils.DataTypePayment.ATTR_USE_CREDIT_CARD;
import static it.academy.utils.DataTypePayment.ATTR_USE_PHONE_NUMBER;

public class TypePaymentConverter implements ITypePaymentConverter {
    @Override
    public TypePaymentDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String name = request.getParameter(ATTR_NAME);
        String useCreditCard = request.getParameter(ATTR_USE_CREDIT_CARD);
        String usePhoneNumber = request.getParameter(ATTR_USE_PHONE_NUMBER);

        return TypePaymentDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .name(name)
                .useCreditCard(StringUtils.isBlank(useCreditCard) ? Boolean.FALSE : ON.equals(useCreditCard))
                .usePhoneNumber(StringUtils.isBlank(usePhoneNumber) ? Boolean.FALSE : ON.equals(usePhoneNumber))
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        return new HashMap<>();
    }
}
