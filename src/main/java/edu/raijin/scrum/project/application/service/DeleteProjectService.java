package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.DeletedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.UpdateProjectPort;
import edu.raijin.scrum.project.domain.usecase.DeleteProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProjectService implements DeleteProjectUseCase {

    private final UpdateProjectPort updateProject;
    private final DeletedProjectPublisherPort eventPublisher;

    @Override
    @Transactional
    public void delete(UUID projectId, UUID actorId) {
        Project project = updateProject.findById(projectId).orElse(null);
        if (project == null) {
            return;
        }

        project.delete();
        updateProject.update(project);

        eventPublisher.publishDeletedProject(project, actorId);
    }
}
