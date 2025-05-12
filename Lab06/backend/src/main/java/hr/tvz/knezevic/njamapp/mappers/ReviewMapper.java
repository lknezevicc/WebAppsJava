package hr.tvz.knezevic.njamapp.mappers;

import hr.tvz.knezevic.njamapp.command.ReviewCommand;
import hr.tvz.knezevic.njamapp.dto.ReviewDTO;
import hr.tvz.knezevic.njamapp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDto(Review review);
    Review toEntity(ReviewCommand reviewCommand);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ReviewCommand reviewCommand, @MappingTarget Review review);
}
