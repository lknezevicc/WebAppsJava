package hr.tvz.knezevic.njamapp.controllers;

import hr.tvz.knezevic.njamapp.command.AuthCommand;
import hr.tvz.knezevic.njamapp.dto.jwt.JwtResponseDTO;
import hr.tvz.knezevic.njamapp.dto.jwt.RefreshTokenRequestDTO;
import hr.tvz.knezevic.njamapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticate(@Valid @RequestBody AuthCommand authCommand) {
        return authService.authenticate(authCommand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return authService.refreshToken(refreshTokenRequestDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        authService.delete(refreshTokenRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
