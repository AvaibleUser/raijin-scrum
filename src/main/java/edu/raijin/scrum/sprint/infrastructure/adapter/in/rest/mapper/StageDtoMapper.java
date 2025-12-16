package edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage.AddStageDto;
import edu.raijin.scrum.sprint.infrastructure.adapter.in.rest.dto.stage.StageDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageDtoMapper {

    Stage toDomain(AddStageDto dto);

    StageDto toDto(Stage domain);
}
