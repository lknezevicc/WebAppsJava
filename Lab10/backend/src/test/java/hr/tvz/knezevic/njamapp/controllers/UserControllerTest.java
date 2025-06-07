package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.dto.UserGroupDTO;
import hr.tvz.knezevic.njamapp.dto.UserInfoDTO;
import hr.tvz.knezevic.njamapp.enums.UserRole;
import hr.tvz.knezevic.njamapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @WithMockUser(username = "user123")
    void getCurrentUser_shouldReturnCurrentUser() throws Exception {
        UserInfoDTO mockUser = new UserInfoDTO(
                null,
                "user123",
                "User",
                "User",
                Set.of(new UserGroupDTO(UserRole.USER))
        );

        when(userService.getCurrentUser()).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("user123"))
                .andExpect(jsonPath("$.firstName").value("User"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.userGroups[0].role").value("USER"));
    }

    @Test
    @WithMockUser(username = "user123")
    void getCurrentUser_shouldReturnNotExistingUser() throws Exception {
        when(userService.getCurrentUser()).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/me"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCurrentUser_shouldReturnForbiddenIfNotLoggedIn() throws Exception {
        mockMvc.perform(get("/users/me"))
                .andExpect(status().isForbidden());
    }
}