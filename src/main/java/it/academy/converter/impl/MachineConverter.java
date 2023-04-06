package it.academy.converter.impl;

import it.academy.converter.IMachineConverter;
import it.academy.dto.AddressDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ModelDto;
import it.academy.services.IAddressService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.ModelService;
import org.junit.platform.commons.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;

import static it.academy.utils.DataAddress.ATTR_ADDRESS_ID;
import static it.academy.utils.DataAddress.ATTR_CITIES;
import static it.academy.utils.DataAddress.ATTR_SEARCH_CITIES;
import static it.academy.utils.DataGeneral.ATTR_ID;
import static it.academy.utils.DataMachine.ATTR_MODEL_ID;
import static it.academy.utils.DataMachine.ATTR_SEARCH_SERIAL_NUMBER;
import static it.academy.utils.DataMachine.ATTR_SERIAL_NUMBER;

public class MachineConverter implements IMachineConverter {
    private final IAddressService addressService = new AddressService();

    private final IModelService modelService = new ModelService();

    @Override
    public MachineDto convertToDto(HttpServletRequest request) {
        String id = request.getParameter(ATTR_ID);
        String serialNumber = request.getParameter(ATTR_SERIAL_NUMBER);
        String addressId = request.getParameter(ATTR_ADDRESS_ID);
        String modelId = request.getParameter(ATTR_MODEL_ID);

        AddressDto addressDto = null;
        ModelDto modelDto = null;

        if (StringUtils.isNotBlank(addressId)) {
            addressDto = addressService.findAddressById(Integer.parseInt(addressId));
        }


        if (StringUtils.isNotBlank(modelId)) {
            modelDto = modelService.findModelById(Integer.parseInt(modelId));
        }

        return MachineDto.builder()
                .id(StringUtils.isBlank(id) ? null : Integer.parseInt(id))
                .serialNumber(serialNumber)
                .address(addressDto)
                .model(modelDto)
                .build();
    }

    @Override
    public HashMap<String, Object> convertSearchFields(HttpServletRequest request) {
        HashMap<String, Object> searchFields = new HashMap<>();

        String serialNumber = request.getParameter(ATTR_SEARCH_SERIAL_NUMBER);
        searchFields.put(ATTR_SERIAL_NUMBER, serialNumber);

        String[] cities = request.getParameterValues(ATTR_SEARCH_CITIES);
        if (cities != null) {
            cities = Arrays.stream(cities)
                    .filter(StringUtils::isNotBlank)
                    .toList().toArray(new String[0]);
            if (cities.length == 0) {
                cities = null;
            }
        }
        searchFields.put(ATTR_CITIES, cities);

        return searchFields;
    }
}
