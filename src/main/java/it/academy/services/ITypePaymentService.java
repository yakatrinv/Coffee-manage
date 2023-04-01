package it.academy.services;

import it.academy.dto.TypePaymentDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface ITypePaymentService {
    void createTypePayment(TypePaymentDto typePaymentDto);

    void updateTypePayment(TypePaymentDto typePaymentDto);

    TypePaymentDto findTypePaymentById(Serializable id);

    void deleteTypePaymentById(Serializable id);

    Pageable<TypePaymentDto> getPageableRecords(Pageable<TypePaymentDto> pageable);

    List<TypePaymentDto> findAllTypePayments();
}
