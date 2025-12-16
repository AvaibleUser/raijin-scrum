package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Stage;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.StagesEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageEntityMapper {

    @Mapping(target = "sprintId", source = "sprint.id")
    Stage toDomain(StagesEntity entity);

    @Mapping(target = "sprint.id", source = "sprintId")
    StagesEntity toEntity(Stage stage);
}
