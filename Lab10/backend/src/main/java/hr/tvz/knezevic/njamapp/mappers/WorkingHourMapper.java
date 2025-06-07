package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.command.WorkingHourCommand;
import hr.tvz.knezevic.njamapp.model.WorkingHour;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkingHourMapper {
    WorkingHour toEntity(WorkingHourCommand command);
}
