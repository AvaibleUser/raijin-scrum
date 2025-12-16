package edu.raijin.scrum.story.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Story;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.AddStoryDto;
import edu.raijin.scrum.story.infrastructure.adapter.in.rest.dto.story.StoryDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StoryDtoMapper {

    Story toDomain(AddStoryDto storyDto);

    StoryDto toDto(Story story);
}
