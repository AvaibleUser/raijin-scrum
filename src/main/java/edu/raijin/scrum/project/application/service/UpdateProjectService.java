package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.scrum.project.domain.usecase.UpdateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProjectService implements UpdateProjectUseCase {

    private final UpdateProjectPort updateProject;
    private final UpdatedProjectPublisherPort eventPublisher;

    @Override
    public Project update(UUID projectId, Project update) {
        Project project = updateProject.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));

        project.updateFrom(update);
        project.checkValidRegistration();
        Project updated = updateProject.update(project);

        eventPublisher.publishUpdatedProject(updated);
        return updated;
    }
}
