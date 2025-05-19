package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.dto.UserInfoDTO;
import hr.tvz.knezevic.njamapp.model.UserInfo;

import java.util.Optional;

public interface UserService {
    Optional<UserInfoDTO> getCurrentUser();
    Optional<UserInfo> getByUsername(String username);
}
