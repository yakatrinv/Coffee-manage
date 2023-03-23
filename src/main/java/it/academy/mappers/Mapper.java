package it.academy.mappers;

public interface Mapper<TEntity, TDto> {
    TEntity dtoToEntity(TDto dto);

    TDto entityToDto(TEntity entity);
}
