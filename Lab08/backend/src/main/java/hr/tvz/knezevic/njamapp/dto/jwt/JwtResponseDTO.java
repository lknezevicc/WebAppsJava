package hr.tvz.knezevic.njamapp.dto.jwt;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record JwtResponseDTO(
        @NonNull String accessToken,
        @NonNull String refreshToken
) { }
