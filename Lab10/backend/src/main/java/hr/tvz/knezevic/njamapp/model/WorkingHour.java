package hr.tvz.knezevic.njamapp.model;

import hr.tvz.knezevic.njamapp.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "restaurant")
@Entity
@Table(name = "working_hours")
public class WorkingHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private String workingHour;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
