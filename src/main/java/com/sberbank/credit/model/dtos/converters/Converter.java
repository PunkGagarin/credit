package com.sberbank.credit.model.dtos.converters;

public interface Converter<E,D> {

    E convertToEntity(D dto);

    D convertToDto(E entity);
}
