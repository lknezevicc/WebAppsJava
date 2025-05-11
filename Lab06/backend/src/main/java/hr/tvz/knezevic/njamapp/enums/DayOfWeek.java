package hr.tvz.knezevic.njamapp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DayOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String value;

    DayOfWeek(String value) {
        this.value = value;
    }

    @JsonValue
    public String toJson() {
        return value;
    }

    @JsonCreator
    public static DayOfWeek fromJson(String value) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.value.equalsIgnoreCase(value)) {
                return day;
            }
        }
        throw new IllegalArgumentException("Invalid day: " + value);
    }
}