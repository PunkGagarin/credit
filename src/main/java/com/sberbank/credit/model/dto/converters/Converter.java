package com.sberbank.credit.model.dto.converters;

public interface Converter<E,D> {

    E convertToEntity(D dto);

    D convertToDto(E entity);
}
