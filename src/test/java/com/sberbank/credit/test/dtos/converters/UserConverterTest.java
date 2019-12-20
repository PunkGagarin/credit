package com.sberbank.credit.test.dtos.converters;


import com.sberbank.credit.model.dtos.User;
import com.sberbank.credit.model.dtos.converters.Converter;
import com.sberbank.credit.model.dtos.converters.UserConverter;
import com.sberbank.credit.model.entities.UserEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserConverterTest {

    private Converter<UserEntity, User> userConverter = new UserConverter();

    @Test
    public void testConvertToDto() {
        List<UserEntity> entities = Stream.of(
                new UserEntity("Степан Степанович", "stepa229", "123456"),
                new UserEntity("Рамазан Назмар", "rammar", "123321")
        ).collect(Collectors.toList());

        List<User> dtos = entities
                .stream()
                .map(el -> userConverter.convertToDto(el))
                .collect(Collectors.toList());

        Assert.assertEquals("Must be equals", entities.get(0).getFio(), dtos.get(0).getFio());
        Assert.assertEquals("Must be equals", entities.get(0).getLogin(), dtos.get(0).getLogin());
        Assert.assertEquals("Must be equals", entities.get(0).getPassword(), dtos.get(0).getPassword());
    }


}
