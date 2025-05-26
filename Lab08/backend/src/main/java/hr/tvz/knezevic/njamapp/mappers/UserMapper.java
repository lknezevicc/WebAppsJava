package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.dto.UserInfoDTO;
import hr.tvz.knezevic.njamapp.model.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserInfoDTO toDto(UserInfo userInfo);
}
