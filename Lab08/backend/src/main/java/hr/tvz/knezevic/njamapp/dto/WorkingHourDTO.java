package hr.tvz.knezevic.njamapp.dto;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;

public record WorkingHourDTO(
        DayOfWeek dayOfWeek,
        String workingHour
) { }
