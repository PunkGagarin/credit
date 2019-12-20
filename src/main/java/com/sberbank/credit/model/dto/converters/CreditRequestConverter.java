package com.sberbank.credit.model.dto.converters;

import com.sberbank.credit.model.dto.CreditRequest;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("creditRequestConverter")
public class CreditRequestConverter implements Converter<CreditRequestEntity, CreditRequest> {
    @Override
    public CreditRequestEntity convertToEntity(CreditRequest dto) {
        CreditRequestEntity entity = new CreditRequestEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public CreditRequest convertToDto(CreditRequestEntity entity) {
        CreditRequest dto = new CreditRequest();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
