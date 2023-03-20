package it.academy.services;

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
    private String nameCommand;

    private String paramPage;

    private int pageNumber;

    private int pageSize;

    private int offset;

    int lastPageNumber;

    long totalRecords;

    private String sortField;

    private HashMap<String, Object> filteredFields;

    private List<TEntity> records;
}
