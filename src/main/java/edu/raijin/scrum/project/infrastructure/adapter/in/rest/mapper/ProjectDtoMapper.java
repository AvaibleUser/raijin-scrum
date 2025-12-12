package edu.raijin.scrum.project.infrastructure.adapter.in.rest.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.AddProjectDto;
import edu.raijin.scrum.project.infrastructure.adapter.in.rest.dto.project.ProjectDto;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface ProjectDtoMapper {

    Project toDomain(AddProjectDto dto);

    ProjectDto toDto(Project project);
}
