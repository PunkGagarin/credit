package com.sberbank.credit.model.dto.converters;

import com.sberbank.credit.model.dto.Product;
import com.sberbank.credit.model.entity.ProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("productConverter")
public class ProductConverter implements Converter<ProductEntity, Product> {
    @Override
    public ProductEntity convertToEntity(Product dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public Product convertToDto(ProductEntity entity) {
        Product dto = new Product();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
