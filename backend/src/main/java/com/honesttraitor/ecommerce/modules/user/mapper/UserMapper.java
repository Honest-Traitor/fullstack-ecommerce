package com.honesttraitor.ecommerce.modules.user.mapper;

import com.honesttraitor.ecommerce.modules.user.dto.UserResponseDto;
import com.honesttraitor.ecommerce.modules.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto dto);
}
