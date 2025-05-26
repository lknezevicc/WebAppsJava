package hr.tvz.knezevic.njamapp.dto;

import java.util.Set;

public record UserInfoDTO(
    Long id,
    String username,
    String firstName,
    String lastName,
    Set<UserGroupDTO> userGroups
) { }
