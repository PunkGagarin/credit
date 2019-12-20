package com.sberbank.credit.model.dto.converters;

import com.sberbank.credit.model.dto.User;
import com.sberbank.credit.model.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverter implements Converter<UserEntity, User> {
    @Override
    public UserEntity convertToEntity(User dto) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public User convertToDto(UserEntity entity) {
        User dto = new User();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
