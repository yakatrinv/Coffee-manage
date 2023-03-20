package it.academy.repositories;

import it.academy.models.Address;
import it.academy.services.Pageable;

public interface IAddressRepository extends ICrudRepository<Address> {
    Pageable<Address> fillPageable(Pageable<Address> pageable);

    Long getCountRecords(Pageable<Address> pageable);
}
