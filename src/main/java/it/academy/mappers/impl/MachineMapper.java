package it.academy.mappers.impl;

import it.academy.dto.AddressDto;
import it.academy.dto.MachineDto;
import it.academy.dto.ModelDto;
import it.academy.mappers.Mapper;
import it.academy.models.Address;
import it.academy.models.Machine;
import it.academy.models.Model;

public class MachineMapper implements Mapper<Machine, MachineDto> {
    private final Mapper<Address, AddressDto> addressMapper = new AddressMapper();

    private final Mapper<Model, ModelDto> modelMapper = new ModelMapper();

    @Override
    public Machine dtoToEntity(MachineDto machineDto) {
        Address address =
                machineDto.getAddress() == null ? null :
                        addressMapper.dtoToEntity(machineDto.getAddress());

        Model model = machineDto.getModel() == null ? null :
                modelMapper.dtoToEntity(machineDto.getModel());

        return Machine.builder()
                .id(machineDto.getId())
                .serialNumber(machineDto.getSerialNumber())
                .address(address)
                .model(model)
                .build();
    }

    @Override
    public MachineDto entityToDto(Machine machine) {
        AddressDto addressDto =
                machine.getAddress() == null ? null :
                        addressMapper.entityToDto(machine.getAddress());

        ModelDto modelDto = machine.getModel() == null ? null :
                modelMapper.entityToDto(machine.getModel());

        return MachineDto.builder()
                .id(machine.getId())
                .serialNumber(machine.getSerialNumber())
                .address(addressDto)
                .model(modelDto)
                .build();
    }
}
