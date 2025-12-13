package edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.domain.type.ProjectStatus;
import edu.raijin.commons.infrastructure.adapter.rest.dto.scrum.ProjectDto;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddProjectDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectDtoMapper {

    Project toDomain(AddProjectDto dto);

    @Mapping(target = "active", source = "status")
    ProjectDto toDto(Project project);

    default Boolean mapStatus(ProjectStatus status) {
        return status == ProjectStatus.ACTIVE;
    }
}
