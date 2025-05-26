package hr.tvz.knezevic.njamapp.service.impl;

import hr.tvz.knezevic.njamapp.model.RefreshToken;
import hr.tvz.knezevic.njamapp.model.UserInfo;
import hr.tvz.knezevic.njamapp.repository.RefreshTokenRepository;
import hr.tvz.knezevic.njamapp.repository.UserRepository;
import hr.tvz.knezevic.njamapp.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String username) {
        refreshTokenRepository.findByUsername(username)
                .ifPresent(refreshTokenRepository::delete);

        UserInfo user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(3600*8))
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
        }

        return token;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findByRefreshToken_Id(id)
                .ifPresent(userInfo -> {
                    userInfo.setRefreshToken(null);
                    userRepository.save(userInfo);
                });
        refreshTokenRepository.deleteById(id);
    }

}
