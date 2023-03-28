package it.academy.converter;

import it.academy.dto.MachineDto;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IMachineConverter extends IConverter<MachineDto> {
    HashMap<String, Object> convertSearchFields(HttpServletRequest request);
}
