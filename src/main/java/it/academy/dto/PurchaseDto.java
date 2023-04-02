package it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString(exclude = {"machine", "product", "discount"})
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto implements Serializable {
    private Integer id;

    private CustomerDto customer;

    private MachineDto machine;

    private ProductDto product;

    private float price;

    private DiscountDto discount;

    private float sum;

    private CreditCardDto creditCard;

    private TypePaymentDto typePayment;
}
