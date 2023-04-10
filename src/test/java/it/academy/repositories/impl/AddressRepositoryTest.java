package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.models.CreditCard;
import it.academy.repositories.IAddressRepository;
import it.academy.repositories.ICrudRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {
    public static final String MINSK = "Минск";
    public static final String Grodno = "Гродно";
    public static final String Gomel = "Гомель";
    public static final int THREE = 3;
    IAddressRepository addressRepository = new AddressRepository();

    @Test
    void isShouldGetAllCityInAddresses() {
        Address address = Address.builder()
                .city(MINSK)
                .build();

        Address address2 = Address.builder()
                .city(Grodno)
                .build();

        Address address3 = Address.builder()
                .city(Gomel)
                .build();

        Address newAddress = addressRepository.save(address);
        Address newAddress2 = addressRepository.save(address2);
        Address newAddress3 = addressRepository.save(address3);

        List<String> allCities = addressRepository.getAllCity();

        assertAll(
                () -> assertEquals(THREE, allCities.size()),
                () -> assertTrue(allCities.contains(address.getCity())),
                () -> assertTrue(allCities.contains(address2.getCity())),
                () -> assertTrue(allCities.contains(address3.getCity()))
        );

        addressRepository.delete(newAddress.getId());
        addressRepository.delete(newAddress2.getId());
        addressRepository.delete(newAddress3.getId());
    }
}