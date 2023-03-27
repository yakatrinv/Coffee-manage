package it.academy.repositories.impl;

import it.academy.models.Address;
import it.academy.repositories.IAddressRepository;

import static it.academy.utils.Data.ADDRESS_CLASS;

public class AddressRepository extends CrudRepository<Address>
        implements IAddressRepository {
    public AddressRepository() {
        super(ADDRESS_CLASS);
    }
}
