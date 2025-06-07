package hr.tvz.knezevic.njamapp.command;

import jakarta.validation.constraints.NotBlank;

public record AuthCommand(
        @NotBlank
        String username,
        @NotBlank
        String password
) { }
