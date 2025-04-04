package hr.tvz.knezevic.njamapp.command;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerCommand {
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Digits(integer = 10, fraction = 0, message = "Phone number must contain only digits")
    private String phoneNumber;

    @Future(message = "Account valid until date must be in the future")
    private LocalDate accountValidUntil;
}
