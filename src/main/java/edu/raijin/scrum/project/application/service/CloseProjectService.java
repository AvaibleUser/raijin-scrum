package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.CloseProjectPort;
import edu.raijin.scrum.project.domain.usecase.CloseProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloseProjectService implements CloseProjectUseCase {

    private final CloseProjectPort updateProject;
    private final UpdatedProjectPublisherPort eventPublisher;

    @Override
    @Transactional
    public Project close(UUID projectId) {
        Project project = updateProject.findById(projectId)
                .orElseThrow(() -> new ValueNotFoundException("El proyecto no existe"));

        if (updateProject.hasActiveSprintsAttached(projectId)) {
            throw new RequestConflictException("El proyecto tiene sprints registrados aun activos");
        }
        project.close();
        Project updated = updateProject.update(project);

        eventPublisher.publishUpdatedProject(updated);

        return project;
    }
}
