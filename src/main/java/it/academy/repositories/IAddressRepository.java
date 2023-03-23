package it.academy.repositories;

import it.academy.models.Address;
import it.academy.models.pageable.Pageable;

public interface IAddressRepository extends ICrudRepository<Address> {
    Pageable<Address> getPageableRecords(Pageable<Address> pageable);

    Long getCountRecords(Pageable<Address> pageable);
}
