package edu.raijin.scrum.project.infrastructure.adapter.out.persistence.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.infrastructure.adapter.out.persistence.entity.ProjectsEntity;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectEntityMapper {

    Project toDomain(ProjectsEntity entity);

    ProjectsEntity toEntity(Project domain);
}
