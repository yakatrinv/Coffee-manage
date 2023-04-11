package it.academy.services.impl;

import it.academy.dto.TypePaymentDto;
import it.academy.mappers.Mapper;
import it.academy.mappers.impl.PageableMapper;
import it.academy.mappers.impl.TypePaymentMapper;
import it.academy.models.TypePayment;
import it.academy.models.pageable.Pageable;
import it.academy.repositories.ITypePaymentRepository;
import it.academy.repositories.impl.TypePaymentRepository;
import it.academy.services.ITypePaymentService;

import java.io.Serializable;
import java.util.List;

public class TypePaymentService implements ITypePaymentService {
    private final ITypePaymentRepository repository
            = new TypePaymentRepository();

    private final Mapper<TypePayment, TypePaymentDto> mapper = new TypePaymentMapper();

    private final Mapper<Pageable<TypePayment>, Pageable<TypePaymentDto>> mapperP =
            new PageableMapper<>(mapper);

    @Override
    public void createTypePayment(TypePaymentDto typePaymentDto) {
        TypePayment typePayment = mapper.dtoToEntity(typePaymentDto);
        typePayment = repository.save(typePayment);
        mapper.entityToDto(typePayment);
    }

    @Override
    public void updateTypePayment(TypePaymentDto typePaymentDto) {
        TypePayment typePayment = mapper.dtoToEntity(typePaymentDto);
        typePayment = repository.update(typePayment);
        mapper.entityToDto(typePayment);
    }

    @Override
    public TypePaymentDto findTypePaymentById(Serializable id) {
        TypePayment typePayment = repository.getById(id);
        return mapper.entityToDto(typePayment);
    }

    @Override
    public void deleteTypePaymentById(Serializable id) {
        repository.delete(id);
    }

    @Override
    public List<TypePaymentDto> findAllTypePayments() {
        List<TypePayment> typePayments = repository.getAll();
        return typePayments
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    @Override
    public Pageable<TypePaymentDto> getPageableRecords(Pageable<TypePaymentDto> pageableDto) {
        Pageable<TypePayment> pageable = mapperP.dtoToEntity(pageableDto);
        return mapperP.entityToDto(repository.getPageableRecords(pageable));
    }
}
