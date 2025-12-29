package edu.raijin.scrum.sprint.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.SprintEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Sprint;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface SprintEventMapper {

    SprintEvent toEvent(Sprint Sprint);
}
