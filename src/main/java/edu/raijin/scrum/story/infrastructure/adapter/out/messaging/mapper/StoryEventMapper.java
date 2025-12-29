package edu.raijin.scrum.story.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.story.domain.model.Story;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface StoryEventMapper {

    StoryEvent toEvent(Story Story);
}
