package it.academy.converter.impl;

import it.academy.converter.IMachineConverter;
import it.academy.dto.AddressDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ModelDto;
import it.academy.services.IAddressService;
import it.academy.services.IModelService;
import it.academy.services.impl.AddressService;
import it.academy.services.impl.ModelService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static it.academy.utils.Data.ATTR_ADDRESS_ID;
import static it.academy.utils.Data.ATTR_ID;
import static it.academy.utils.Data.ATTR_MODEL_ID;
import static it.academy.utils.Data.ATTR_SEARCH_SERIAL_NUMBER;
import static it.academy.utils.Data.ATTR_SERIAL_NUMBER;

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

        if (!isEmpty(addressId)) {
            addressDto = addressService.findAddressById(Integer.valueOf(addressId));
        }


        if (!isEmpty(modelId)) {
            modelDto = modelService.findModelById(Integer.valueOf(modelId));
        }

        return MachineDto.builder()
                .id(isEmpty(id) ? null : Integer.valueOf(id))
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

        return searchFields;
    }

    private boolean isEmpty(String value) {
        return (value == null || value.isEmpty());
    }
}
