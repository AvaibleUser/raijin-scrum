package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.UpdatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.scrum.project.domain.usecase.DeleteProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProjectService implements DeleteProjectUseCase {

    private final UpdateProjectPort updateProject;
    private final UpdatedProjectPublisherPort eventPublisher;

    @Override
    public void delete(UUID projectId) {
        Project project = updateProject.findById(projectId).orElse(null);
        if (project == null) {
            return;
        }

        project.delete();
        updateProject.update(project);

        eventPublisher.publishUpdatedProject(project);
    }
}
