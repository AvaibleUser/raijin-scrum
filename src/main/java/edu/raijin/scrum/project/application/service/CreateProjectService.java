package edu.raijin.scrum.project.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.BadRequestException;
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
    public UUID create(Project project) {
        try {
            project.checkValidRegistration();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }

        UUID id = registerProject.create(project);
        project.updateId(id);

        eventPublisher.publishCreatedProject(project);
        return id;
    }
}
