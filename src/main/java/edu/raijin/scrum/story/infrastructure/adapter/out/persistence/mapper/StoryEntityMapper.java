package edu.raijin.scrum.story.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.infrastructure.adapter.out.persistence.entity.StoriesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryEntityMapper {

    @Mapping(target = "stageId", source = "stage.id")
    @Mapping(target = "productOwnerId", source = "productOwner.id")
    @Mapping(target = "developerId", source = "developer.id")
    Story toDomain(StoriesEntity entity);

    @Mapping(target = "stage.id", source = "stageId")
    @Mapping(target = "productOwner.id", source = "productOwnerId")
    @Mapping(target = "developer.id", source = "developerId")
    StoriesEntity toEntity(Story story);
}
