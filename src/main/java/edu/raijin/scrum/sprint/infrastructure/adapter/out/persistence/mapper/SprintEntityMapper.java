package edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.infrastructure.adapter.out.persistence.entity.SprintsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SprintEntityMapper {

    @Mapping(target = "projectId", source = "project.id")
    Sprint toSprint(SprintsEntity entity);

    @Mapping(target = "project.name", constant = "EL PROYECTO")
    @Mapping(target = "project.id", source = "projectId")
    SprintsEntity toEntity(Sprint sprint);
}
