package it.academy.mappers.impl;

import it.academy.dto.TypePaymentDto;
import it.academy.mappers.Mapper;
import it.academy.models.TypePayment;

public class TypePaymentMapper implements Mapper<TypePayment, TypePaymentDto> {
    @Override
    public TypePayment dtoToEntity(TypePaymentDto typePaymentDto) {
        return TypePayment.builder()
                .id(typePaymentDto.getId())
                .name(typePaymentDto.getName())
                .build();
    }

    @Override
    public TypePaymentDto entityToDto(TypePayment typePayment) {
        return TypePaymentDto.builder()
                .id(typePayment.getId())
                .name(typePayment.getName())
                .build();
    }
}
