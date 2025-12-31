package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.scrum.project.domain.model.Project;
import edu.raijin.scrum.project.domain.port.messaging.CreatedProjectPublisherPort;
import edu.raijin.scrum.project.domain.port.persistence.RegisterProjectPort;
import edu.raijin.scrum.project.domain.usecase.CreateProjectUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProjectService implements CreateProjectUseCase {

    private final RegisterProjectPort registerProject;
    private final CreatedProjectPublisherPort eventPublisher;

    @Override
    @Transactional
    public UUID create(Project project, UUID actorId) {
        project.checkValidRegistration();

        UUID id = registerProject.create(project);
        project.updateId(id);

        eventPublisher.publishCreatedProject(project, actorId);
        return id;
    }
}
