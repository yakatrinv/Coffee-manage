package it.academy.services;

import it.academy.dto.PurchaseDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;

public interface IPurchaseService {
    PurchaseDto createPurchase(PurchaseDto purchaseDto);

    PurchaseDto updatePurchase(PurchaseDto purchaseDto);

    PurchaseDto findPurchaseById(Serializable id);

    void deletePurchaseById(Serializable id);

    Pageable<PurchaseDto> getPageableRecords(Pageable<PurchaseDto> pageable);
}
