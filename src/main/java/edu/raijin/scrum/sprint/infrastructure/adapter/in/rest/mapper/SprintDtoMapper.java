package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.AddSprintDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.SprintDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.sprint.UpdateSprintDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintDtoMapper {

    Sprint toDomain(AddSprintDto dto);

    Sprint toDomain(UpdateSprintDto dto);

    SprintDto toDto(Sprint domain);
}
