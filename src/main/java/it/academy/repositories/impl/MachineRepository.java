package it.academy.repositories.impl;

import it.academy.models.Machine;
import it.academy.repositories.IMachineRepository;

import static it.academy.utils.Data.MACHINE_CLASS;

public class MachineRepository extends CrudRepository<Machine>
        implements IMachineRepository {
    public MachineRepository() {
        super(MACHINE_CLASS);
    }
}
