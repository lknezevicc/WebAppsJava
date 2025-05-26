package hr.tvz.knezevic.njamapp.service;

import hr.tvz.knezevic.njamapp.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
    void delete(RefreshToken token);
}
