package hr.tvz.knezevic.njamapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.knezevic.njamapp.command.AuthCommand;
import hr.tvz.knezevic.njamapp.dto.jwt.JwtResponseDTO;
import hr.tvz.knezevic.njamapp.dto.jwt.RefreshTokenRequestDTO;
import hr.tvz.knezevic.njamapp.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void authenticate_shouldReturnJwtResponse_whenValidCredentials() throws Exception {
        AuthCommand authCommand = new AuthCommand("username", "password");
        JwtResponseDTO jwtResponse = new JwtResponseDTO("token", "refreshToken");

        when(authService.authenticate(any(AuthCommand.class)))
                .thenReturn(Optional.of(jwtResponse));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authCommand)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token"))
                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    void authenticate_shouldReturnBadRequest_whenAuthenticationFails() throws Exception {
        AuthCommand authCommand = new AuthCommand("username", "wrongPassword");

        when(authService.authenticate(any(AuthCommand.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authCommand)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void refreshToken_shouldReturnJwtResponse_whenValidRefreshToken() throws Exception {
        RefreshTokenRequestDTO refreshTokenRequest = new RefreshTokenRequestDTO("validRefreshToken");
        JwtResponseDTO jwtResponse = new JwtResponseDTO("newToken", "newRefreshToken");

        when(authService.refreshToken(any(RefreshTokenRequestDTO.class)))
                .thenReturn(Optional.of(jwtResponse));

        mockMvc.perform(post("/auth/refreshToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshTokenRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("newToken"))
                .andExpect(jsonPath("$.refreshToken").value("newRefreshToken"));
    }

    @Test
    void refreshToken_shouldReturnBadRequest_whenRefreshFails() throws Exception {
        RefreshTokenRequestDTO refreshTokenRequest = new RefreshTokenRequestDTO("invalidRefreshToken");

        when(authService.refreshToken(any(RefreshTokenRequestDTO.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/auth/refreshToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshTokenRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "user123")
    void logout_shouldReturnNoContent() throws Exception {
        RefreshTokenRequestDTO refreshTokenRequest = new RefreshTokenRequestDTO("someRefreshToken");

        doNothing().when(authService).delete(any(RefreshTokenRequestDTO.class));

        mockMvc.perform(post("/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshTokenRequest)))
                .andExpect(status().isNoContent());

        verify(authService, times(1)).delete(any(RefreshTokenRequestDTO.class));
    }
}