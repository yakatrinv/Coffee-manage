package it.academy.models.pageable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Builder
@Getter
@Setter
public class Pageable<TEntity> implements Serializable {
    private int pageNumber;

    private int pageSize;

    private int lastPageNumber;

    private String sortField;

    private HashMap<String, Object> searchFields;

    private List<TEntity> records;
}
