package us.devfast.cheetah.dto;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import us.devfast.cheetah.entity.UserEntity;

/**
 * Setting auto map object from dto to entity and the reverse side
 */

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MapperDto {
    /**************************************************************************
     * Auto Map form UserEntity to Dto
     *************************************************************************/
    UserDto convertUserDto(UserEntity user);


    /**************************************************************************
     * Auto Map form Dto to UserEntity
     *************************************************************************/
    UserEntity convertUserEntity(LoginDto userLogin);

    UserEntity convertUserEntity(RegisterDto userRegister);

}
