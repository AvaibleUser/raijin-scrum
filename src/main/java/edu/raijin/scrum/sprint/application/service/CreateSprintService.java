package edu.raijin.scrum.sprint.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.scrum.sprint.domain.model.Sprint;
import edu.raijin.scrum.sprint.domain.port.messaging.CreatedSprintPublisherPort;
import edu.raijin.scrum.sprint.domain.port.persistence.RegisterSprintPort;
import edu.raijin.scrum.sprint.domain.usecase.CreateSprintUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSprintService implements CreateSprintUseCase {

    private final RegisterSprintPort registerSprint;
    private final CreatedSprintPublisherPort eventPublisher;

    @Override
    public Sprint create(UUID projectId, Sprint sprint) {
        if (!registerSprint.existsProject(projectId)) {
            throw new ValueNotFoundException("El proyecto no se encuentra registrado");
        }
        sprint.checkValidRegistration();

        Sprint created = registerSprint.create(projectId, sprint);

        eventPublisher.publishCreatedSprint(created);
        return created;
    }
}
