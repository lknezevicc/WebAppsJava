package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.command.AuthCommand;
import hr.tvz.knezevic.njamapp.dto.jwt.JwtResponseDTO;
import hr.tvz.knezevic.njamapp.dto.jwt.RefreshTokenRequestDTO;
import hr.tvz.knezevic.njamapp.model.RefreshToken;
import hr.tvz.knezevic.njamapp.service.AuthService;
import hr.tvz.knezevic.njamapp.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public Optional<JwtResponseDTO> authenticate(AuthCommand authCommand) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authCommand.username(), authCommand.password()));

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authentication.getAuthorities().stream()
                .map(auth -> auth.getAuthority().startsWith("ROLE_") ? auth.getAuthority() : "ROLE_" + auth.getAuthority())
                .toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authCommand.username());

        return Optional.ofNullable(JwtResponseDTO.builder()
                .accessToken(jwtService.generateToken(claims, authCommand.username()))
                .refreshToken(refreshToken.getToken())
                .build());
    }

    @Override
    public Optional<JwtResponseDTO> refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.token())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    List<String> roles = userInfo.getUserGroups().stream()
                            .map(group -> "ROLE_" + group.getRole().name())
                            .toList();

                    String accessToken = jwtService.generateToken(
                            Map.of("roles", roles),
                            userInfo.getUsername()
                    );

                    return JwtResponseDTO.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshTokenRequestDTO.token())
                                    .build();
                });
    }

    @Override
    public void delete(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        refreshTokenService.findByToken(refreshTokenRequestDTO.token())
                .ifPresent(refreshTokenService::delete);
    }
}
