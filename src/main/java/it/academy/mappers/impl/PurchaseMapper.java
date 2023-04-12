package it.academy.mappers.impl;

import it.academy.dto.CreditCardDto;
import it.academy.dto.CustomerDto;
import it.academy.dto.DiscountDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ProductDto;
import it.academy.dto.PurchaseDto;
import it.academy.dto.TypePaymentDto;
import it.academy.mappers.Mapper;
import it.academy.models.CreditCard;
import it.academy.models.Customer;
import it.academy.models.Discount;
import it.academy.models.Machine;
import it.academy.models.Product;
import it.academy.models.Purchase;
import it.academy.models.TypePayment;

public class PurchaseMapper implements Mapper<Purchase, PurchaseDto> {
    private final Mapper<Customer, CustomerDto> customerMapper = new CustomerMapper();

    private final Mapper<Machine, MachineDto> machineMapper = new MachineMapper();

    private final Mapper<Product, ProductDto> productMapper = new ProductMapper();

    private final Mapper<Discount, DiscountDto> discountMapper = new DiscountMapper();

    private final Mapper<TypePayment, TypePaymentDto> typePaymentMapper = new TypePaymentMapper();

    private final Mapper<CreditCard, CreditCardDto> creditCardMapper = new CreditCardMapper();

    @Override
    public Purchase dtoToEntity(PurchaseDto purchaseDto) {
        Customer customer =
                purchaseDto.getCustomer() == null ? null :
                        customerMapper.dtoToEntity(purchaseDto.getCustomer());

        Machine machine = purchaseDto.getMachine() == null ? null :
                machineMapper.dtoToEntity(purchaseDto.getMachine());

        Product product = purchaseDto.getProduct() == null ? null :
                productMapper.dtoToEntity(purchaseDto.getProduct());

        Discount discount = purchaseDto.getDiscount() == null ? null :
                discountMapper.dtoToEntity(purchaseDto.getDiscount());

        TypePayment typePayment = purchaseDto.getTypePayment() == null ? null :
                typePaymentMapper.dtoToEntity(purchaseDto.getTypePayment());

        CreditCard creditCard = purchaseDto.getCreditCard() == null ? null :
                creditCardMapper.dtoToEntity(purchaseDto.getCreditCard());

        return Purchase.builder()
                .id(purchaseDto.getId())
                .customer(customer)
                .machine(machine)
                .product(product)
                .price(purchaseDto.getPrice())
                .discount(discount)
                .sum(purchaseDto.getSum())
                .typePayment(typePayment)
                .creditCard(creditCard)
                .build();
    }

    @Override
    public PurchaseDto entityToDto(Purchase purchase) {
        CustomerDto customer =
                purchase.getCustomer() == null ? null :
                        customerMapper.entityToDto(purchase.getCustomer());

        MachineDto machine = purchase.getMachine() == null ? null :
                machineMapper.entityToDto(purchase.getMachine());

        ProductDto product = purchase.getProduct() == null ? null :
                productMapper.entityToDto(purchase.getProduct());

        DiscountDto discount = purchase.getDiscount() == null ? null :
                discountMapper.entityToDto(purchase.getDiscount());

        TypePaymentDto typePayment = purchase.getTypePayment() == null ? null :
                typePaymentMapper.entityToDto(purchase.getTypePayment());

        CreditCardDto creditCard = purchase.getCreditCard() == null ? null :
                creditCardMapper.entityToDto(purchase.getCreditCard());

        return PurchaseDto.builder()
                .id(purchase.getId())
                .customer(customer)
                .machine(machine)
                .product(product)
                .price(purchase.getPrice())
                .discount(discount)
                .sum(purchase.getSum())
                .typePayment(typePayment)
                .creditCard(creditCard)
                .createDate(purchase.getCreateDate())
                .build();
    }
}
