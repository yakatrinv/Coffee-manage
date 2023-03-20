package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.mappers.IAddressMapper;
import it.academy.models.Address;
import it.academy.services.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.DataUI.DEFAULT_PAGE_SIZE;
import static it.academy.utils.DataUI.FIRST_PAGE;
import static it.academy.utils.Utils.isEmpty;

public class AddressMapper implements IAddressMapper {
    @Override
    public Address dtoToEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
    }

    @Override
    public AddressDto entityToDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    @Override
    public AddressDto requestToDto(HttpServletRequest request) {
        String id = request.getParameter("id");
        String city = request.getParameter("city");
        String street = request.getParameter("street");

        return AddressDto.builder()
                .id(isEmpty(id) ? null : Integer.valueOf(id))
                .city(city)
                .street(street)
                .build();
    }

    @Override
    public Pageable<Address> getPageable(HttpServletRequest request) {
        Pageable<Address> pageable = Pageable.<Address>builder().build();

        String command = request.getParameter("command");
        String paramPage = request.getParameter("paramPage");
        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        String orderField = request.getParameter("orderField");
        String city = request.getParameter("city");
        String street = request.getParameter("street");

        HashMap<String, Object> filteredFields = new HashMap<>();
        filteredFields.put("city", city);
        filteredFields.put("street", street);

        pageable.setNameCommand(command);
        pageable.setParamPage(paramPage);
        pageable.setPageNumber(isEmpty(pageNumber) ? FIRST_PAGE : Integer.parseInt(pageNumber));
        pageable.setPageSize(isEmpty(pageSize) ? DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        pageable.setSortField(orderField);
        pageable.setFilteredFields(filteredFields);

        return pageable;
    }

    @Override
    public Pageable<AddressDto> pageableToDto(Pageable<Address> pageable) {
        List<AddressDto> addresses = pageable.getRecords()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return Pageable.<AddressDto>builder()
                .nameCommand(pageable.getNameCommand())
                .paramPage(pageable.getParamPage())
                .pageNumber(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .offset(pageable.getOffset())
                .lastPageNumber(pageable.getLastPageNumber())
                .totalRecords(pageable.getTotalRecords())
                .sortField(pageable.getSortField())
                .filteredFields(pageable.getFilteredFields())
                .records(addresses)
                .build();
    }
}
