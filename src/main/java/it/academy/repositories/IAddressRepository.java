package it.academy.repositories;

import it.academy.models.Address;

import java.util.List;

public interface IAddressRepository extends ICrudRepository<Address> {
    List<String> getAllCity();
}
