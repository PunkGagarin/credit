package com.sberbank.credit.model.dtos.converters;

import com.sberbank.credit.model.dtos.Order;
import com.sberbank.credit.model.entities.OrderEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("orderConverter")
public class OrderConverter implements Converter<OrderEntity, Order> {

    @Override
    public OrderEntity convertToEntity(Order dto) {
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public Order convertToDto(OrderEntity entity) {
        Order dto = new Order();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
