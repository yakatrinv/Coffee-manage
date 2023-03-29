package it.academy.services;

import it.academy.dto.ModelDto;
import it.academy.models.pageable.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IModelService {
    void createModel(ModelDto modelDto);

    void updateModel(ModelDto modelDto);

    ModelDto findModelById(Serializable id);

    void deleteModelById(Serializable id);

    Pageable<ModelDto> getPageableRecords(Pageable<ModelDto> pageable);

    List<ModelDto> findAllModels();
}
