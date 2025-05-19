package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.dto.UserInfoDTO;
import hr.tvz.knezevic.njamapp.mappers.UserMapper;
import hr.tvz.knezevic.njamapp.model.UserInfo;
import hr.tvz.knezevic.njamapp.repository.UserRepository;
import hr.tvz.knezevic.njamapp.service.UserService;
import hr.tvz.knezevic.njamapp.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserInfoDTO> getCurrentUser() {
        return userRepository.findByUsername(SecurityUtil.getCurrentUsername())
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserInfo> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
