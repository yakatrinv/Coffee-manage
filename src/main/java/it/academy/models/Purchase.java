package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.DataDiscount.ATTR_DISCOUNT_ID;
import static it.academy.utils.DataMachine.ATTR_MACHINE_ID;
import static it.academy.utils.DataProduct.ATTR_PRODUCT_ID;
import static it.academy.utils.DataTypePayment.ATTR_TYPE_PAYMENT_ID;

@Builder
@Getter
@Setter
@ToString(exclude = {"machine", "product", "discount"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Purchase extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = ATTR_MACHINE_ID)
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = ATTR_PRODUCT_ID)
    private Product product;

    @Column
    private float price;

    @ManyToOne
    @JoinColumn(name = ATTR_DISCOUNT_ID)
    private Discount discount;

    @Column
    private float sum;

    @ManyToOne
    @JoinColumn(name = ATTR_CREDIT_CARD_ID)
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = ATTR_TYPE_PAYMENT_ID)
    private TypePayment typePayment;
}
