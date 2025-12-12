package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.scrum.project.domain.usecase.CloseProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloseProjectService implements CloseProjectUseCase {

    private final UpdateProjectPort updateProject;
    private final UpdatedProjectPublisherPort eventPublisher;

    @Override
    public Project close(UUID projectId) {
        Project project = updateProject.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));

        project.close();
        Project updated = updateProject.update(project);

        eventPublisher.publishUpdatedProject(updated);

        return project;
    }
}
