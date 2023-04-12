package it.academy.mappers.impl;

import it.academy.dto.TypePaymentDto;
import it.academy.mappers.Mapper;
import it.academy.models.TypePayment;

public class TypePaymentMapper implements Mapper<TypePayment, TypePaymentDto> {
    @Override
    public TypePayment dtoToEntity(TypePaymentDto typePaymentDto) {
        return typePaymentDto == null ? null : TypePayment.builder()
                .id(typePaymentDto.getId())
                .name(typePaymentDto.getName())
                .useCreditCard(typePaymentDto.getUseCreditCard())
                .usePhoneNumber(typePaymentDto.getUsePhoneNumber())
                .build();
    }

    @Override
    public TypePaymentDto entityToDto(TypePayment typePayment) {
        return typePayment == null ? null : TypePaymentDto.builder()
                .id(typePayment.getId())
                .name(typePayment.getName())
                .useCreditCard(typePayment.getUseCreditCard())
                .usePhoneNumber(typePayment.getUsePhoneNumber())
                .build();
    }
}
