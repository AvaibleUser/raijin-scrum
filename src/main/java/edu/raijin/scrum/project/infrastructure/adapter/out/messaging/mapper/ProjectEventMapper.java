package edu.raijin.scrum.project.infrastructure.adapter.out.messaging.mapper;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.infrastructure.adapter.messaging.event.shared.Audit;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.scrum.project.domain.model.Project;

@Adapter
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = IGNORE)
public interface ProjectEventMapper {

    @Mapping(target = "description", source = "project.description")
    @Mapping(target = "audit", source = "audit")
    ProjectEvent toEvent(Project project, Audit audit);
}
