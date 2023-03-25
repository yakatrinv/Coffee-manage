package it.academy.mappers.impl;

import it.academy.mappers.Mapper;
import it.academy.models.pageable.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class PageableMapper<TEntity, TDto> implements Mapper<Pageable<TEntity>, Pageable<TDto>> {
    private final Mapper<TEntity, TDto> mapper;

    public PageableMapper(Mapper<TEntity, TDto> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Pageable<TEntity> dtoToEntity(Pageable<TDto> dto) {
        List<TEntity> records = dto.getRecords() == null ? null :
                dto.getRecords()
                        .stream()
                        .map(mapper::dtoToEntity)
                        .collect(Collectors.toList());
        return Pageable.<TEntity>builder()
                .pageSize(dto.getPageSize())
                .lastPageNumber(dto.getLastPageNumber())
                .pageNumber(dto.getPageNumber())
                .sortField(dto.getSortField())
                .searchFields(dto.getSearchFields())
                .records(records)
                .build();
    }

    @Override
    public Pageable<TDto> entityToDto(Pageable<TEntity> entity) {
        List<TDto> records = entity.getRecords() == null ? null :
                entity.getRecords()
                        .stream()
                        .map(mapper::entityToDto)
                        .collect(Collectors.toList());
        return Pageable.<TDto>builder()
                .pageSize(entity.getPageSize())
                .lastPageNumber(entity.getLastPageNumber())
                .pageNumber(entity.getPageNumber())
                .sortField(entity.getSortField())
                .searchFields(entity.getSearchFields())
                .records(records)
                .build();
    }
}
