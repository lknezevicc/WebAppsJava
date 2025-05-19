package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.command.AuthCommand;
import hr.tvz.knezevic.njamapp.dto.jwt.JwtResponseDTO;
import hr.tvz.knezevic.njamapp.dto.jwt.RefreshTokenRequestDTO;

import java.util.Optional;

public interface AuthService {
    Optional<JwtResponseDTO> authenticate(AuthCommand authCommand);
    Optional<JwtResponseDTO> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);
    void delete(RefreshTokenRequestDTO refreshTokenRequestDTO);
}
