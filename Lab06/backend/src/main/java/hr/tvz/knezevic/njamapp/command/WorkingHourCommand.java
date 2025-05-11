package hr.tvz.knezevic.njamapp.command;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkingHourCommand {
    @NotNull(message = "Day of week is required")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Working hour is required")
    private String workingHour;
}
