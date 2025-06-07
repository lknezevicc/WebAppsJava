package hr.tvz.knezevic.njamapp.command;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import jakarta.validation.constraints.NotNull;

public record WorkingHourCommand(
    @NotNull(message = "Day of week is required")
    DayOfWeek dayOfWeek,

    @NotNull(message = "Working hour is required")
    String workingHour
) { }
