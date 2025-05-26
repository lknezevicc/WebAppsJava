package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(source = "user.username", target = "username")
    ReviewDTO toDto(Review review);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    Review toEntity(ReviewCommand command);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    void updateEntity(ReviewCommand command, @MappingTarget Review review);
}
