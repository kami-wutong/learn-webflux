package com.ryanqy.util.converter;

import com.ryanqy.entity.UserEntity;
import com.ryanqy.vo.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author tong.wu
 * created on 2023/12/18
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserConverter {

    public abstract UserEntity convertToUserEntity(UserRegisterRequest request);

}
